package co.edu.uniquindio.poo.parcial_1.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainViewController {

    @FXML
    private void onGestionClientes(ActionEvent event) {
        abrirVentana("/co/edu/uniquindio/poo/parcial_1/ClienteView.fxml", "Gestión de Clientes");
    }

    @FXML
    private void onGestionEntrenadores(ActionEvent event) {
        abrirVentana("/co/edu/uniquindio/poo/parcial_1/EntrenadorView.fxml", "Gestión de Entrenadores");
    }

    @FXML
    private void onGestionPlanes(ActionEvent event) {
        abrirVentana("/co/edu/uniquindio/poo/parcial_1/PlanView.fxml", "Gestión de Planes de Entrenamiento");
    }

    @FXML
    private void onCrearInscripcion(ActionEvent event) {
        abrirVentana("/co/edu/uniquindio/poo/parcial_1/InscripcionView.fxml", "Gestión de Inscripciones");
    }

    @FXML
    private void onConsultas(ActionEvent event) {
        abrirVentana("/co/edu/uniquindio/poo/parcial_1/InscripcionView.fxml", "Consultas e Historiales");
    }

    @FXML
    private void onAlertas(ActionEvent event) {
        abrirVentana("/co/edu/uniquindio/poo/parcial_1/AlertasView.fxml", "Alertas y Estado de Membresías");
    }

    private void abrirVentana(String pathFXML, String titulo) {
        try {
            URL resource = getClass().getResource(pathFXML);
            if (resource == null) {
                mostrarMensajeError("Error de Carga", "No se encontró la vista FXML: " + pathFXML);
                return;
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarMensajeError("Error de Carga", "Error al abrir la ventana: " + e.getMessage());
        }
    }

    private void mostrarMensajeError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}