package com.example.javafx_css;

import javafx.beans.property.*;

public class Order {

    private final StringProperty orderId;
    private final StringProperty customer;
    private final DoubleProperty amount;
    private final BooleanProperty urgent;

    public Order(String orderId, String customer, double amount, boolean urgent) {
        this.orderId = new SimpleStringProperty(orderId);
        this.customer = new SimpleStringProperty(customer);
        this.amount = new SimpleDoubleProperty(amount);
        this.urgent = new SimpleBooleanProperty(urgent);
    }

    public String getOrderId() { return orderId.get(); }
    public StringProperty orderIdProperty() { return orderId; }

    public String getCustomer() { return customer.get(); }
    public void setCustomer(String customer) { this.customer.set(customer); }
    public StringProperty customerProperty() { return customer; }

    public double getAmount() { return amount.get(); }
    public void setAmount(double amount) { this.amount.set(amount); }
    public DoubleProperty amountProperty() { return amount; }

    public boolean isUrgent() { return urgent.get(); }
    public void setUrgent(boolean urgent) { this.urgent.set(urgent); }
    public BooleanProperty urgentProperty() { return urgent; }
}
