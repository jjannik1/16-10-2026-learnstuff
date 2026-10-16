module com.example.javafx_eventos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_eventos to javafx.fxml;
    exports com.example.javafx_eventos;
}