package co.edu.uniquindio.poo.parcial_1.viewController;

import co.edu.uniquindio.poo.parcial_1.model.Cliente;
import co.edu.uniquindio.poo.parcial_1.model.Gimnasio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ClienteViewController {

    @FXML private TextField txtId, txtNombre, txtTelefono, txtCorreo, txtEdad;
    @FXML private TableView<Cliente> tblClientes;
    @FXML private TableColumn<Cliente, String> colId, colNombre, colTelefono, colCorreo;
    @FXML private TableColumn<Cliente, Integer> colEdad;

    private final ObservableList<Cliente> listaClientesTabla = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        listaClientesTabla.addAll(Gimnasio.getInstancia().getListaClientes());
        tblClientes.setItems(listaClientesTabla);
    }

    @FXML
    private void handleRegistrarCliente() {
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String correo = txtCorreo.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            if (id.isEmpty() || nombre.isEmpty()) {
                mostrarAlerta("Error", "ID y Nombre son campos obligatorios.");
                return;
            }

            Cliente nuevoCliente = new Cliente(id, nombre, telefono, correo, edad, LocalDate.now());
            Gimnasio.getInstancia().getListaClientes().add(nuevoCliente);
            listaClientesTabla.add(nuevoCliente);

            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "La edad debe ser un valor numérico.");
        }
    }

    private void limpiarCampos() {
        txtId.clear(); txtNombre.clear(); txtTelefono.clear(); txtCorreo.clear(); txtEdad.clear();
    }

    private void mostrarAlerta(String titulo, String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}