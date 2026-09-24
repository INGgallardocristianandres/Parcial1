package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscripcionViewController {

    @FXML private ComboBox<Cliente> cmbClientes;
    @FXML private ComboBox<Entrenador> cmbEntrenadores;
    @FXML private ComboBox<String> cmbPlanes;
    @FXML private ComboBox<String> cmbTipoServicio;
    @FXML private ComboBox<EstadoPlan> cmbNuevoEstado;

    @FXML private TextField txtCodigoInscripcion;
    @FXML private TextField txtDescuento;

    @FXML private TableView<Inscripcion> tblInscripciones;
    @FXML private TableColumn<Inscripcion, String> colCodigo;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, String> colEntrenador;
    @FXML private TableColumn<Inscripcion, String> colEstado;
    @FXML private TableColumn<Inscripcion, Double> colTotal;

    private final List<ServicioAdicional> serviciosTemporales = new ArrayList<>();
    private final ObservableList<Inscripcion> listaInscripcionesTabla = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbTipoServicio.setItems(FXCollections.observableArrayList("Evaluación Médica", "Entrenamiento Especializado"));
        cmbPlanes.setItems(FXCollections.observableArrayList("Plan Básico", "Plan Premium", "Plan Personalizado"));
        cmbNuevoEstado.setItems(FXCollections.observableArrayList(EstadoPlan.values()));

        configurarComboClientes();
        configurarComboEntrenadores();

        cargarDatosCombos();
        configurarTabla();
    }

    private void configurarComboClientes() {
        cmbClientes.setConverter(new StringConverter<Cliente>() {
            @Override
            public String toString(Cliente cliente) {
                // Se usa getId() definido en Persona
                return (cliente != null) ? cliente.getId() + " - " + cliente.getNombre() : "";
            }

            @Override
            public Cliente fromString(String string) {
                return null;
            }
        });
    }

    private void configurarComboEntrenadores() {
        cmbEntrenadores.setConverter(new StringConverter<Entrenador>() {
            @Override
            public String toString(Entrenador entrenador) {
                // Se usa getId() definido en Persona
                return (entrenador != null) ? entrenador.getId() + " - " + entrenador.getNombre() : "";
            }

            @Override
            public Entrenador fromString(String string) {
                return null;
            }
        });
    }

    public void cargarDatosCombos() {
        cmbClientes.setItems(FXCollections.observableArrayList(Gimnasio.getInstancia().getListaClientes()));
        cmbEntrenadores.setItems(FXCollections.observableArrayList(Gimnasio.getInstancia().getListaEntrenadores()));
    }

    private void configurarTabla() {
        colCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        colCliente.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getCliente() != null ? cellData.getValue().getCliente().getNombre() : "N/A"));
        colPlan.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getPlan() != null ? cellData.getValue().getPlan().getNombre() : "N/A"));
        colEntrenador.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getEntrenadorResponsable() != null ? cellData.getValue().getEntrenadorResponsable().getNombre() : "Sin asignar"));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getPlan() != null ? cellData.getValue().getPlan().getEstado().toString() : "N/A"));
        colTotal.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().calcularPagoTotal()).asObject());

        listaInscripcionesTabla.setAll(Gimnasio.getInstancia().getListaInscripciones());
        tblInscripciones.setItems(listaInscripcionesTabla);
    }

    @FXML
    private void handleAgregarServicio() {
        String tipo = cmbTipoServicio.getValue();
        if (tipo == null) {
            mostrarAlerta("Atención", "Seleccione un servicio adicional de la lista.");
            return;
        }

        ServicioAdicionalFactory factory = tipo.contains("Médica") ? new ServiciosSaludFactory() : new ServiciosEspecializadosFactory();
        ServicioAdicional servicio = factory.crearServicio("SERV-" + (serviciosTemporales.size() + 1), tipo, "Servicio Adicional: " + tipo, 25000.0);
        serviciosTemporales.add(servicio);

        mostrarInfo("Servicio Agregado", "Se agregó " + tipo + " a la inscripción en construcción.");
    }

    @FXML
    private void handleCrearInscripcion() {
        Cliente cliente = cmbClientes.getValue();
        Entrenador entrenador = cmbEntrenadores.getValue();
        String codigo = txtCodigoInscripcion.getText();
        String tipoPlan = cmbPlanes.getValue();

        if (cliente == null || codigo == null || codigo.trim().isEmpty() || tipoPlan == null) {
            mostrarAlerta("Campos Incompletos", "Diligencie el código, cliente y selecciones el tipo de plan.");
            return;
        }

        double descuento = 0.0;
        try {
            if (!txtDescuento.getText().trim().isEmpty()) {
                descuento = Double.parseDouble(txtDescuento.getText().trim());
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El valor del descuento debe ser numérico.");
            return;
        }

        // Creación del Plan según la Factory correspondiente
        PlanEntrenamiento plan;
        if ("Plan Premium".equals(tipoPlan)) {
            plan = new PlanPremiumFactory("PL-PREM", "Plan Premium", "Acceso VIP completo", 1, 120000.0, true, true).crearPlan();
        } else if ("Plan Personalizado".equals(tipoPlan)) {
            plan = new PlanPersonalizadoFactory("PL-PERS", "Plan Personalizado", "Seguimiento personalizado", 1, 90000.0, 8, Especialidad.HIPERTROFIA, "Aumento muscular").crearPlan();
        } else {
            plan = new PlanBasicoFactory("PL-BAS", "Plan Básico", "Acceso a máquinas generales", 1, 60000.0, true).crearPlan();
        }

        // Construcción con el Patrón Builder
        Inscripcion.Builder builder = new Inscripcion.Builder();
        builder.setCodigo(codigo)
                .setFecha(LocalDate.now())
                .setCliente(cliente)
                .setEntrenador(entrenador)
                .setPlan(plan)
                .setDescuento(descuento);

        for (ServicioAdicional s : serviciosTemporales) {
            builder.agregarServicio(s);
        }

        Inscripcion nuevaInscripcion = builder.build();

        if (Gimnasio.getInstancia().registrarInscripcion(nuevaInscripcion)) {
            listaInscripcionesTabla.add(nuevaInscripcion);
            limpiarFormulario();
            mostrarInfo("Éxito", "Inscripción creada satisfactoriamente.");
        }
    }

    @FXML
    private void handleCambiarEstado() {
        Inscripcion seleccion = tblInscripciones.getSelectionModel().getSelectedItem();
        EstadoPlan nuevoEstado = cmbNuevoEstado.getValue();

        if (seleccion == null || nuevoEstado == null) {
            mostrarAlerta("Atención", "Seleccione una inscripción de la tabla y el nuevo estado.");
            return;
        }

        seleccion.getPlan().setEstado(nuevoEstado);
        tblInscripciones.refresh();
        mostrarInfo("Éxito", "Estado actualizado correctamente.");
    }

    @FXML
    private void onVolver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void limpiarFormulario() {
        txtCodigoInscripcion.clear();
        txtDescuento.setText("0.0");
        cmbClientes.getSelectionModel().clearSelection();
        cmbEntrenadores.getSelectionModel().clearSelection();
        cmbPlanes.getSelectionModel().clearSelection();
        cmbTipoServicio.getSelectionModel().clearSelection();
        serviciosTemporales.clear();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void mostrarInfo(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

