package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.Cliente;
import co.edu.uniquindio.poo.parcial_1.model.Gimnasio;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ClienteViewController {

    @FXML private TextField txtId;
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtCorreo;
    @FXML private TextField txtEdad;
    @FXML private DatePicker dpFechaRegistro;

    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colTelefono;
    @FXML private TableColumn<Cliente, String> colCorreo;
    @FXML private TableColumn<Cliente, Integer> colEdad;
    @FXML private TableColumn<Cliente, LocalDate> colFecha;

    private final ObservableList<Cliente> listaClientesTabla = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dpFechaRegistro.setValue(LocalDate.now());

        // Configuración de columnas mapeando directamente con los métodos del modelo
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colTelefono.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        colCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
        colEdad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEdad()).asObject());
        colFecha.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFechaRegistro()));

        listaClientesTabla.setAll(Gimnasio.getInstancia().getListaClientes());
        tblClientes.setItems(listaClientesTabla);
    }

    @FXML
    private void handleGuardarCliente() {
        String id = txtId.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String edadStr = txtEdad.getText();
        LocalDate fecha = dpFechaRegistro.getValue();

        if (id == null || id.isBlank() || nombre == null || nombre.isBlank() || edadStr == null || edadStr.isBlank() || fecha == null) {
            mostrarAlerta("Campos Requeridos", "Por favor complete al menos ID, Nombre, Edad y Fecha.");
            return;
        }

        int edad;
        try {
            edad = Integer.parseInt(edadStr.trim());
        } catch (NumberFormatException e) {
            mostrarAlerta("Formato Inválido", "La edad debe ser un número entero.");
            return;
        }

        Cliente nuevoCliente = new Cliente(id, nombre, telefono, correo, edad, fecha);

        // Agregar al Singleton del Gimnasio
        Gimnasio.getInstancia().getListaClientes().add(nuevoCliente);
        listaClientesTabla.add(nuevoCliente);

        limpiarFormulario();
        mostrarInfo("Éxito", "Cliente registrado correctamente.");
    }

    @FXML
    private void handleEliminarCliente() {
        Cliente seleccionado = tblClientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Atención", "Seleccione un cliente de la tabla.");
            return;
        }

        Gimnasio.getInstancia().getListaClientes().remove(seleccionado);
        listaClientesTabla.remove(seleccionado);
        mostrarInfo("Éxito", "Cliente eliminado correctamente.");
    }

    @FXML
    private void onVolver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void limpiarFormulario() {
        txtId.clear();
        txtNombre.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(LocalDate.now());
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
