package com.cubes4.CUBES4.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date orderDate;
    private boolean isSupplierOrder; // true if it's a supplier order, false if customer order

    public enum OrderStatus {
        PREPARATION,
        READY,
        SENT,
        DELIVERED,
        RECEIVED
    }

    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // If it's a fournisseur order, we may store a reference to the fournisseur
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;

    public Long getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date dateCommande) {
        this.orderDate = dateCommande;
    }

    public boolean isSupplierOrder() {
        return isSupplierOrder;
    }

    public void setSupplierOrder(boolean supplierOrder) {
        isSupplierOrder = supplierOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus statut) {
        this.status = statut;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
}

