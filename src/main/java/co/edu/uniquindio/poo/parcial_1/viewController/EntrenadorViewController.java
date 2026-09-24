package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.Entrenador;
import co.edu.uniquindio.poo.parcial_1.model.Especialidad;
import co.edu.uniquindio.poo.parcial_1.model.Gimnasio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class EntrenadorViewController {

    @FXML private TextField txtId, txtNombre, txtTelefono, txtCorreo, txtTarifa;
    @FXML private ComboBox<Especialidad> cmbEspecialidad;
    @FXML private TableView<Entrenador> tblEntrenadores;
    @FXML private TableColumn<Entrenador, String> colId, colNombre;
    @FXML private TableColumn<Entrenador, Especialidad> colEspecialidad;
    @FXML private TableColumn<Entrenador, Double> colTarifa;

    private final ObservableList<Entrenador> listaEntrenadoresTabla = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cmbEspecialidad.setItems(FXCollections.observableArrayList(Especialidad.values()));

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        colTarifa.setCellValueFactory(new PropertyValueFactory<>("tarifaHora"));

        listaEntrenadoresTabla.addAll(Gimnasio.getInstancia().getListaEntrenadores());
        tblEntrenadores.setItems(listaEntrenadoresTabla);
    }

    @FXML
    private void handleRegistrarEntrenador() {
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            Especialidad especialidad = cmbEspecialidad.getValue();
            double tarifa = Double.parseDouble(txtTarifa.getText());

            if (id.isEmpty() || nombre.isEmpty() || especialidad == null) {
                mostrarAlerta("Error", "Complete todos los campos requeridos.");
                return;
            }

            Entrenador nuevoEntrenador = new Entrenador(id, nombre, telefono, correo, especialidad, tarifa);
            Gimnasio.getInstancia().getListaEntrenadores().add(nuevoEntrenador);
            listaEntrenadoresTabla.add(nuevoEntrenador);

            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "La tarifa debe ser un número válido.");
        }
    }

    private void limpiarCampos() {
        txtId.clear(); txtNombre.clear(); txtTelefono.clear(); txtCorreo.clear(); txtTarifa.clear();
        cmbEspecialidad.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}