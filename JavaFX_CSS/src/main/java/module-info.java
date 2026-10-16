module com.example.javafx_css {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_css to javafx.fxml;
    exports com.example.javafx_css;
}