module com.example.javafx_mvc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_mvc to javafx.fxml;
    exports com.example.javafx_mvc;
}