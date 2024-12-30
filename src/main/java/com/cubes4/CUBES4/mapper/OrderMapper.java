package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.models.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setSupplierOrder(order.isSupplierOrder());
        dto.setStatus(order.getStatus());
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderDate(dto.getOrderDate());
        order.setSupplierOrder(dto.isSupplierOrder());
        order.setStatus(dto.getStatus());
        return order;
    }

    public void updateEntity(Order existingOrder, OrderDTO dto) {
        existingOrder.setOrderDate(dto.getOrderDate());
        existingOrder.setSupplierOrder(dto.isSupplierOrder());
        existingOrder.setStatus(dto.getStatus());
    }
}
