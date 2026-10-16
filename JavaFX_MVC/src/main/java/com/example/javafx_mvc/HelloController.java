package com.example.javafx_mvc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    // Inyección de componentes declarados en el FXML
    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String> cbRol;
    @FXML private TextArea txtBio;
    @FXML private Label lblStatus;
    @FXML private TableView<String> tablaUsers;
    @FXML private TableColumn<String, String> colNombre;
    @FXML private TableColumn<String, String> colEmail;

    /**
     * Inicialización del controlador tras la carga del documento FXML.
     */
    @FXML
    public void initialize() {
        cbRol.getItems().addAll("Administrador", "Operador", "Invitado");
        cbRol.getSelectionModel().selectFirst();
    }

    @FXML
    private void onGuardar(ActionEvent event) {
        if (txtNombre.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
            lblStatus.setText("❌ Error: Nombre y Email son obligatorios");
            lblStatus.setStyle("-fx-text-fill: red;");
        } else {
            lblStatus.setText("✅ Usuario registrado: " + txtNombre.getText());
            lblStatus.setStyle("-fx-text-fill: green;");
            limpiarCampos();
        }
    }

    @FXML
    private void onLimpiar(ActionEvent event) {
        limpiarCampos();
        lblStatus.setText("🟢 Estado: Listo");
        lblStatus.setStyle("-fx-text-fill: black;");
    }

    @FXML
    private void onSalir(ActionEvent event) {
        System.exit(0);
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtEmail.clear();
        txtBio.clear();
        cbRol.getSelectionModel().selectFirst();
    }
}
