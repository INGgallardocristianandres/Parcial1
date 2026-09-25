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

import java.util.List;
import java.util.stream.Collectors;

public class AlertasViewController {

    @FXML private TableView<Inscripcion> tblAlertas;
    @FXML private TableColumn<Inscripcion, String> colCodigo;
    @FXML private TableColumn<Inscripcion, String> colCliente;
    @FXML private TableColumn<Inscripcion, String> colPlan;
    @FXML private TableColumn<Inscripcion, String> colEstado;
    @FXML private TableColumn<Inscripcion, Double> colTotal;

    private final ObservableList<Inscripcion> listaInscripcionesInactivas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCodigo()));
        colCliente.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getCliente() != null ? cell.getValue().getCliente().getNombre() : "N/A"));
        colPlan.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPlan() != null ? cell.getValue().getPlan().getNombre() : "N/A"));
        colEstado.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPlan() != null ? cell.getValue().getPlan().getEstado().toString() : "N/A"));
        colTotal.setCellValueFactory(cell -> new SimpleDoubleProperty(cell.getValue().calcularPagoTotal()).asObject());

        cargarAlertas();
    }

    private void cargarAlertas() {
        List<Inscripcion> inactivos = Gimnasio.getInstancia().getListaInscripciones().stream()
                .filter(i -> i.getPlan() != null &&
                        (i.getPlan().getEstado() == EstadoPlan.SUSPENDIDO || i.getPlan().getEstado() == EstadoPlan.SUSPENDIDO))
                .collect(Collectors.toList());

        listaInscripcionesInactivas.setAll(inactivos);
        tblAlertas.setItems(listaInscripcionesInactivas);
    }

    @FXML
    private void handleReactivarPlan() {
        Inscripcion seleccion = tblAlertas.getSelectionModel().getSelectedItem();
        if (seleccion == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Seleccione una inscripción de la lista.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        seleccion.getPlan().setEstado(EstadoPlan.ACTIVO);
        cargarAlertas();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "El plan ha sido reactivado a estado ACTIVO.", ButtonType.OK);
        alert.showAndWait();
    }

    @FXML
    private void onVolver(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}


