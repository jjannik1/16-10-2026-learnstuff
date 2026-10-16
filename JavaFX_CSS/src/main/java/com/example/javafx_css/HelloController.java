package com.example.javafx_css;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    // Pseudoclase personalizada para aplicar estilo CSS a pedidos urgentes
    private static final PseudoClass URGENT_PSEUDO_CLASS = PseudoClass.getPseudoClass("urgent");

    @FXML
    private TableView<Order> ordersTable;
    @FXML
    private TableColumn<Order, String> colId;
    @FXML
    private TableColumn<Order, String> colCustomer;
    @FXML
    private TableColumn<Order, Double> colAmount;
    @FXML
    private TableColumn<Order, Boolean> colUrgent;

    @FXML
    private TextField txtOrderId;
    @FXML
    private TextField txtCustomer;
    @FXML
    private TextField txtAmount;
    @FXML
    private CheckBox chkUrgent;

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnInlineDemo;
    @FXML
    private Button btnParentDemo;
    @FXML
    private VBox parentScopeBox;

    private final ObservableList<Order> orderList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Configuración de columnas del TableView
        colId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colUrgent.setCellValueFactory(new PropertyValueFactory<>("urgent"));

        // 2. RowFactory con soporte para la pseudoclase :urgent
        ordersTable.setRowFactory(tv -> {
            TableRow<Order> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldVal, newVal) -> {
                boolean isUrgent = newVal != null && newVal.isUrgent();
                row.pseudoClassStateChanged(URGENT_PSEUDO_CLASS, isUrgent);
            });
            return row;
        });

        // 3. Cargar datos de prueba
        orderList.addAll(
                new Order("ORD-2024-001", "Acme Corporation", 12500.50, true),
                new Order("ORD-2024-002", "Globex Logistics", 4300.00, false),
                new Order("ORD-2024-003", "Soylent Tech", 8920.75, false),
                new Order("ORD-2024-004", "Initech Systems", 15400.00, true)
        );
        ordersTable.setItems(orderList);

        // 4. Selección en la tabla -> Formulario
        ordersTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selected) -> {
            if (selected != null) {
                txtOrderId.setText(selected.getOrderId());
                txtCustomer.setText(selected.getCustomer());
                txtAmount.setText(String.valueOf(selected.getAmount()));
                chkUrgent.setSelected(selected.isUrgent());
            }
        });

        // 5. Demostración de precedencia: Asignar hoja CSS a nivel de Parent
        parentScopeBox.getStylesheets().add(
                getClass().getResource("parent-override.css").toExternalForm()
        );
    }

    @FXML
    private void handleSave() {
        String id = txtOrderId.getText().trim();
        String customer = txtCustomer.getText().trim();
        double amount = 0.0;
        try {
            amount = Double.parseDouble(txtAmount.getText().trim());
        } catch (NumberFormatException ignored) {
        }

        boolean urgent = chkUrgent.isSelected();

        // Actualizar o crear nuevo
        Order existing = orderList.stream().filter(o -> o.getOrderId().equals(id)).findFirst().orElse(null);
        if (existing != null) {
            existing.setCustomer(customer);
            existing.setAmount(amount);
            existing.setUrgent(urgent);
            ordersTable.refresh();
        } else if (!id.isEmpty()) {
            orderList.add(new Order(id, customer, amount, urgent));
        }
    }

    @FXML
    private void handleDelete() {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            orderList.remove(selected);
            handleClear();
        }
    }

    @FXML
    private void handleClear() {
        txtOrderId.clear();
        txtCustomer.clear();
        txtAmount.clear();
        chkUrgent.setSelected(false);
        ordersTable.getSelectionModel().clearSelection();
    }
}