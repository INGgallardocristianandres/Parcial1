package co.edu.uniquindio.poo.parcial_1;

import co.edu.uniquindio.poo.parcial_1.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            cargarDatosPrueba();

            URL fxmlLocation = getClass().getResource("/co/edu/uniquindio/poo/parcial_1/MainView.fxml");
            if (fxmlLocation == null) {
                System.err.println("ERROR: No se encontró el archivo MainMenuView.fxml en resources.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            primaryStage.setTitle("Sistema de Gestión - SmartGym");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarDatosPrueba() {
        Gimnasio gimnasio = Gimnasio.getInstancia();
        gimnasio.getListaClientes().add(new Cliente("1094001", "Carlos Pérez", "3100000000", "carlos@mail.com", 25, LocalDate.now()));
        gimnasio.getListaClientes().add(new Cliente("1094002", "María Gómez", "3200000000", "maria@mail.com", 30, LocalDate.now()));

        gimnasio.getListaEntrenadores().add(new Entrenador("E-101", "Juan Entrenador", "3000000000", "juan@gym.com", Especialidad.HIPERTROFIA, 25000.0));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
