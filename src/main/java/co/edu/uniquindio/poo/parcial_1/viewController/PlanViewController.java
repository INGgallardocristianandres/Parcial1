package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.*;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
public class PlanViewController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtDuracion;
    @FXML private ComboBox<String> cmbTipoPlan;

    @FXML private TableView<PlanEntrenamiento> tblPlanes;
    @FXML private TableColumn<PlanEntrenamiento, String> colCodigo;
    @FXML private TableColumn<PlanEntrenamiento, String> colNombre;
    @FXML private TableColumn<PlanEntrenamiento, String> colTipo;
    @FXML private TableColumn<PlanEntrenamiento, Integer> colDuracion;
    @FXML private TableColumn<PlanEntrenamiento, Double> colPrecio;

    private final ObservableList<PlanEntrenamiento> listaPlanes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbTipoPlan.setItems(FXCollections.observableArrayList("Plan Básico", "Plan Premium", "Plan Personalizado"));

        colCodigo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCodigo()));
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colTipo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getClass().getSimpleName()));
        colDuracion.setCellValueFactory(cell -> new SimpleIntegerProperty(cell.getValue().getDuracionMeses()).asObject());

        // Se utiliza directamente el método getValorMensual() heredado de PlanEntrenamiento
        colPrecio.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getValorMensual()).asObject());

        // Carga de datos iniciales de prueba
        listaPlanes.add(new PlanBasicoFactory("PL-BAS-1", "Plan Básico Estándar", "Acceso a máquinas generales", 1, 60000.0, true).crearPlan());
        listaPlanes.add(new PlanPremiumFactory("PL-PREM-1", "Plan Premium Gold", "Acceso VIP total y zonas húmedas", 1, 120000.0, true, true).crearPlan());
        listaPlanes.add(new PlanPersonalizadoFactory("PL-PERS-1", "Plan Personalizado Musculación", "Atención 1 a 1", 1, 90000.0, 8, Especialidad.HIPERTROFIA, "Hipertrofia").crearPlan());

        tblPlanes.setItems(listaPlanes);
    }

    @FXML
    private void handleCrearPlan() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String tipo = cmbTipoPlan.getValue();
        String precioStr = txtPrecio.getText();
        String duracionStr = txtDuracion.getText();

        if (codigo == null || codigo.isBlank() || nombre == null || nombre.isBlank() || tipo == null || precioStr == null || precioStr.isBlank()) {
            mostrarAlerta("Campos Incompletos", "Diligencie todos los campos requeridos correctamente.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr.trim());
            int duracion = Integer.parseInt(duracionStr.trim());

            PlanEntrenamiento nuevoPlan;
            if ("Plan Premium".equals(tipo)) {
                nuevoPlan = new PlanPremiumFactory(codigo, nombre, "Acceso VIP", duracion, precio, true, true).crearPlan();
            } else if ("Plan Personalizado".equals(tipo)) {
                nuevoPlan = new PlanPersonalizadoFactory(codigo, nombre, "Seguimiento personalizado", duracion, precio, 4, Especialidad.HIPERTROFIA, "Aumento de masa").crearPlan();
            } else {
                nuevoPlan = new PlanBasicoFactory(codigo, nombre, "Acceso básico", duracion, precio, true).crearPlan();
            }

            listaPlanes.add(nuevoPlan);
            limpiar();
            mostrarInfo("Éxito", "Plan creado correctamente con el patrón Factory Method.");
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Ingrese valores numéricos válidos en Precio y Duración.");
        }
    }

    @FXML
    private void onVolver(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void limpiar() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtDuracion.setText("1");
        cmbTipoPlan.getSelectionModel().clearSelection();
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