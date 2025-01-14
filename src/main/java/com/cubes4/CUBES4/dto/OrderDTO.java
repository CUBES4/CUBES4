package com.cubes4.CUBES4.dto;

import java.util.List;

public class OrderDTO {

    private Long id;
    private String orderDate;
    private boolean supplierOrder;
    private String status;
    private Long customerId;
    private Long supplierId;
    private String customerName;
    private String supplierName;
    private List<OrderLineDTO> items; // Lignes de commande
    private Double totalPrice;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isSupplierOrder() {
        return supplierOrder;
    }

    public void setSupplierOrder(boolean supplierOrder) {
        this.supplierOrder = supplierOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<OrderLineDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderLineDTO> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
