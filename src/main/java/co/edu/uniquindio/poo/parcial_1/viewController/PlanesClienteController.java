package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class PlanesClienteController {

    @FXML private TextField txtIdentificacion;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtEmail;

    @FXML private ComboBox<String> cmbTipoPlan;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;

    @FXML private TableView<PlanEntrenamiento> tblPlanes;
    @FXML private TableColumn<PlanEntrenamiento, String> colCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, Integer> colDuracion;
    @FXML private TableColumn<PlanEntrenamiento, Double> colPrecio;
    @FXML private TableColumn<PlanEntrenamiento, EstadoPlan> colEstado;

    private ObservableList<PlanEntrenamiento> listaPlanesCargados = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbTipoPlan.setItems(FXCollections.observableArrayList("Básico", "Premium", "Personalizado"));
        cmbEspecialidad.setItems(FXCollections.observableArrayList(Especialidad.values()));

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<>("duracionMeses"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("valorMensual"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tblPlanes.setItems(listaPlanesCargados);
    }

    @FXML
    private void handleRegistrarCliente() {
        String id = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String tel = txtTelefono.getText();
        String email = txtEmail.getText();

        if (id.isEmpty() || nombre.isEmpty()) {
            mostrarAlerta("Error de Validación", "Por favor complete los campos de identificación y nombre.");
            return;
        }

        Cliente nuevoCliente = new Cliente(id, nombre, tel, email, 25, LocalDate.now());
        // TODO: aquí se conectaría con la fachada/Singleton del gimnasio (Gimnasio.getInstancia())
        // cuando esa clase exista en el proyecto.

        mostrarInfo("Éxito", "Cliente " + nombre + " registrado correctamente.");
        limpiarCamposCliente();
    }

    @FXML
    private void handleClonarPlan() {
        String tipoSeleccionado = cmbTipoPlan.getValue();

        if (tipoSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de plan.");
            return;
        }

        PlanFactory factory;

        switch (tipoSeleccionado) {
            case "Básico":
                factory = new PlanBasicoFactory("PLN-BAS", "Plan Básico Gimnasio", "Acceso a máquinas", 1, 50000.0, true);
                break;
            case "Premium":
                factory = new PlanPremiumFactory("PLN-PREM", "Plan VIP Total", "Acceso VIP y Clases", 12, 120000.0, true, true);
                break;
            case "Personalizado":
                Especialidad esp = cmbEspecialidad.getValue();
                if (esp == null) {
                    mostrarAlerta("Error", "Debe seleccionar una especialidad para el plan personalizado.");
                    return;
                }
                factory = new PlanPersonalizadoFactory("PLN-PERS", "Plan Personalizado", "Entrenamiento a medida", 3, 150000.0, 12, esp, "Aumento de masa");
                break;
            default:
                return;
        }

        PlanEntrenamiento planOriginal = factory.crearPlan();
        PlanEntrenamiento planClonado = planOriginal.clonar();

        listaPlanesCargados.add(planClonado);
        mostrarInfo("Patrón Prototype Aplicado", "Se ha clonado exitosamente una instancia de " + planClonado.getNombre());
    }

    private void limpiarCamposCliente() {
        txtIdentificacion.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtEmail.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
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