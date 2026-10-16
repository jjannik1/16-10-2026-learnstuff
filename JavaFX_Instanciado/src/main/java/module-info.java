module com.example.javafx_instanciado {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafx_instanciado to javafx.fxml;
    exports com.example.javafx_instanciado;
}