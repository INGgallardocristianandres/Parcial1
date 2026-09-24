module co.edu.uniquindio.poo.parcial_1 {
    requires javafx.controls;
    requires javafx.fxml;

    exports co.edu.uniquindio.poo.parcial_1;
    exports co.edu.uniquindio.poo.parcial_1.model;
    exports co.edu.uniquindio.poo.parcial_1.viewController;

    opens co.edu.uniquindio.poo.parcial_1.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial_1.model to javafx.base;
}