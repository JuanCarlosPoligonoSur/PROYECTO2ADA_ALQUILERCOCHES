module com.mycompany.ada_proyecto2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.ada_proyecto2 to javafx.fxml;
    exports com.mycompany.ada_proyecto2;
}
