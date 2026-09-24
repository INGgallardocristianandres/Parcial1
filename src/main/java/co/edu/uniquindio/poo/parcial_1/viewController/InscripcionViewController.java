package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la vista para la gestión de inscripciones y servicios adicionales.
 */
public class InscripcionViewController {

    @FXML private TextField txtCodigoInscripcion;
    @FXML private TextField txtDescuento;
    @FXML private ComboBox<String> cmbTipoServicio;

    @FXML private TableView<Inscripcion> tblInscripciones;
    @FXML private TableColumn<Inscripcion, String> colCodigo;
    @FXML private TableColumn<Inscripcion, Double> colTotal;

    private final List<ServicioAdicional> serviciosTemporales = new ArrayList<>();
    private final ObservableList<Inscripcion> listaInscripcionesTabla = FXCollections.observableArrayList();

    /**
     * Inicializa las opciones del desplegable y vincula las columnas de la tabla.
     */
    @FXML
    public void initialize() {
        if (cmbTipoServicio != null) {
            cmbTipoServicio.setItems(FXCollections.observableArrayList(
                    "Servicio Salud",
                    "Servicio Especializado"
            ));
        }

        if (colCodigo != null && colTotal != null && tblInscripciones != null) {
            colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

            colTotal.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleDoubleProperty(cellData.getValue().calcularPagoTotal()).asObject()
            );


            listaInscripcionesTabla.addAll(Gimnasio.getInstancia().getListaInscripciones());
            tblInscripciones.setItems(listaInscripcionesTabla);
        }
    }

    /**
     * Agrega un servicio adicional a la lista previa usando el patrón Factory.
     */
    @FXML
    private void handleAgregarServicio() {
        String tipo = cmbTipoServicio.getValue();
        if (tipo == null || tipo.trim().isEmpty()) {
            mostrarAlerta("Atención", "Debe seleccionar un tipo de servicio adicional.");
            return;
        }

        ServicioAdicionalFactory factory;
        if (tipo.equals("Servicio Salud")) {
            factory = new ServiciosSaludFactory();
        } else {
            factory = new ServiciosEspecializadosFactory();
        }

        String codigoServicio = "SERVICIO-" + (serviciosTemporales.size() + 1);
        ServicioAdicional servicio = factory.crearServicio(
                codigoServicio,
                tipo,
                "Servicio de " + tipo,
                25000.0
        );

        serviciosTemporales.add(servicio);
        mostrarInfo("Servicio Agregado", "Se agregó el servicio " + tipo + " a la inscripción actual.");
    }

    /**
     * Construye la inscripción utilizando el patrón Builder y la registra.
     */
    @FXML
    private void handleCrearInscripcion() {
        String codigo = txtCodigoInscripcion.getText();
        String descTexto = txtDescuento.getText();

        if (codigo == null || codigo.trim().isEmpty()) {
            mostrarAlerta("Error de Validación", "Ingrese un código para la inscripción.");
            return;
        }

        double descuento = 0.0;
        try {
            if (descTexto != null && !descTexto.trim().isEmpty()) {
                descuento = Double.parseDouble(descTexto);
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "El porcentaje de descuento debe ser un valor numérico.");
            return;
        }


        Cliente clientePrueba = new Cliente("123", "Cristian", "3001234567", "cristian@email.com", 22, LocalDate.now());
        PlanEntrenamiento planPrueba = new PlanBasico("P1", "Plan Básico", "Acceso a máquinas", 1, 50000, true);

        Inscripcion.Builder builder = new Inscripcion.Builder();
        builder.setCodigo(codigo)
                .setFecha(LocalDate.now())
                .setCliente(clientePrueba)
                .setPlan(planPrueba)
                .setDescuento(descuento);

        for (ServicioAdicional servicio : serviciosTemporales) {
            builder.agregarServicio(servicio);
        }

        Inscripcion nuevaInscripcion = builder.build();


        Gimnasio.getInstancia().registrarInscripcion(nuevaInscripcion);
        listaInscripcionesTabla.add(nuevaInscripcion);

        limpiarCampos();
        mostrarInfo("Inscripción Creada", "La inscripción se registró exitosamente.");
    }

    /**
     * Limpia los componentes de entrada de la interfaz gráfica.
     */
    private void limpiarCampos() {
        if (txtCodigoInscripcion != null) txtCodigoInscripcion.clear();
        if (txtDescuento != null) txtDescuento.clear();
        if (cmbTipoServicio != null) cmbTipoServicio.getSelectionModel().clearSelection();
        serviciosTemporales.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}