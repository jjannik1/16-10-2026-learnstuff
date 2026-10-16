module com.example.pruebajvfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pruebajvfx to javafx.fxml;
    exports com.example.pruebajvfx;
}