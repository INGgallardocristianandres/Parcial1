package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.Entrenador;
import co.edu.uniquindio.poo.parcial_1.model.Especialidad;
import co.edu.uniquindio.poo.parcial_1.model.Gimnasio;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class EntrenadorViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtTarifa;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;

    @FXML private TableView<Entrenador> tblEntrenadores;
    @FXML private TableColumn<Entrenador, String> colId;
    @FXML private TableColumn<Entrenador, String> colNombre;
    @FXML private TableColumn<Entrenador, String> colTelefono;
    @FXML private TableColumn<Entrenador, String> colCorreo;
    @FXML private TableColumn<Entrenador, String> colEspecialidad;
    @FXML private TableColumn<Entrenador, Double> colTarifa;

    private final ObservableList<Entrenador> listaEntrenadoresTabla = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbEspecialidad.setItems(FXCollections.observableArrayList(Especialidad.values()));

        colId.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getId()));
        colNombre.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getNombre()));
        colTelefono.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTelefono()));
        colCorreo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCorreo()));
        colEspecialidad.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getEspecialidad() != null ? cell.getValue().getEspecialidad().toString() : ""));
        colTarifa.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().getTarifaHora()).asObject());

        listaEntrenadoresTabla.setAll(Gimnasio.getInstancia().getListaEntrenadores());
        tblEntrenadores.setItems(listaEntrenadoresTabla);
    }

    @FXML
    private void handleGuardarEntrenador() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        Especialidad especialidad = cmbEspecialidad.getValue();
        String tarifaStr = txtTarifa.getText();

        if (id == null || id.isBlank() || nombre == null || nombre.isBlank() || especialidad == null || tarifaStr == null || tarifaStr.isBlank()) {
            mostrarAlerta("Campos Incompletos", "Por favor complete todos los datos requeridos.");
            return;
        }

        try {
            double tarifa = Double.parseDouble(tarifaStr.trim());
            Entrenador nuevo = new Entrenador(id, nombre, telefono, correo, especialidad, tarifa);
            Gimnasio.getInstancia().getListaEntrenadores().add(nuevo);
            listaEntrenadoresTabla.add(nuevo);
            limpiar();
            mostrarInfo("Éxito", "Entrenador guardado correctamente.");
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "La tarifa debe ser un número válido.");
        }
    }

    @FXML
    private void handleEliminarEntrenador() {
        Entrenador seleccionado = tblEntrenadores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Gimnasio.getInstancia().getListaEntrenadores().remove(seleccionado);
            listaEntrenadoresTabla.remove(seleccionado);
            mostrarInfo("Éxito", "Entrenador eliminado.");
        } else {
            mostrarAlerta("Atención", "Seleccione un entrenador de la tabla.");
        }
    }

    @FXML
    private void onVolver(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void limpiar() {
        txtId.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtTarifa.clear();
        cmbEspecialidad.getSelectionModel().clearSelection();
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