package co.edu.uniquindio.poo.parcial_1.viewController;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainViewController {

    @FXML private TabPane mainTabPane;
    @FXML private Tab tabInscripciones;
    @FXML private Tab tabClientes;
    @FXML private Tab tabEntrenadores;

    @FXML
    public void mostrarInscripciones() {
        if (mainTabPane != null && tabInscripciones != null) {
            mainTabPane.getSelectionModel().select(tabInscripciones);
        }
    }

    @FXML
    public void mostrarClientes() {
        if (mainTabPane != null && tabClientes != null) {
            mainTabPane.getSelectionModel().select(tabClientes);
        }
    }

    @FXML
    public void mostrarEntrenadores() {
        if (mainTabPane != null && tabEntrenadores != null) {
            mainTabPane.getSelectionModel().select(tabEntrenadores);
        }
    }
}