package com.cubes4.CUBES4.dto;

import com.cubes4.CUBES4.models.Order.OrderStatus;

import java.util.Date;

public class OrderDTO {

    private Long id;
    private Date orderDate;
    private boolean isSupplierOrder;
    private OrderStatus status;

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
