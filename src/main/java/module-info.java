module co.edu.uniquindio.poo.parcial_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.parcial_1 to javafx.fxml;
    opens co.edu.uniquindio.poo.parcial_1.viewController to javafx.fxml;


    opens co.edu.uniquindio.poo.parcial_1.model to javafx.base;

    exports co.edu.uniquindio.poo.parcial_1;
    exports co.edu.uniquindio.poo.parcial_1.controller;
    exports co.edu.uniquindio.poo.parcial_1.viewController;
    exports co.edu.uniquindio.poo.parcial_1.model;
}