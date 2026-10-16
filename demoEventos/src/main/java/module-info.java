module com.example.demoeventos {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demoeventos to javafx.fxml;
    exports com.example.demoeventos;
}