module com.example.pruebacreacionsimple {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pruebacreacionsimple to javafx.fxml;
    exports com.example.pruebacreacionsimple;
}