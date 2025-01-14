package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.dto.OrderLineDTO;
import com.cubes4.CUBES4.models.Customer;
import com.cubes4.CUBES4.models.Order;
import com.cubes4.CUBES4.models.OrderItem;
import com.cubes4.CUBES4.models.Supplier;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate().toString());
        orderDTO.setSupplierOrder(order.isSupplierOrder());
        orderDTO.setStatus(order.getStatus() != null ? order.getStatus().name() : null);
        orderDTO.setCustomerId(order.getCustomer() != null ? order.getCustomer().getId() : null);
        orderDTO.setSupplierId(order.getSupplier() != null ? order.getSupplier().getId() : null);
        orderDTO.setTotalPrice(order.getItems() != null ? order.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getArticle().getUnitPrice())
                .sum() : 0.0);

        // Mapping des items
        if (order.getItems() != null) {
            orderDTO.setItems(order.getItems().stream().map(item -> {
                OrderLineDTO lineDTO = new OrderLineDTO();
                lineDTO.setId(item.getId());
                lineDTO.setArticleName(item.getArticle().getName());
                lineDTO.setQuantity(item.getQuantity());
                lineDTO.setUnitPrice(item.getArticle().getUnitPrice());
                return lineDTO;
            }).collect(Collectors.toList()));
        }

        return orderDTO;
    }

    public OrderLineDTO toOrderLineDTO(OrderItem item) {
        if (item == null) {
            return null;
        }

        OrderLineDTO dto = new OrderLineDTO();
        dto.setId(item.getId());
        dto.setArticleId(item.getArticle().getId());
        dto.setArticleName(item.getArticle().getName());
        dto.setQuantity(item.getQuantity());
        dto.setUnitPrice(item.getArticle().getUnitPrice());
        return dto;
    }

    public Order toEntity(OrderDTO orderDTO, Order existingOrder) {
        Order order = (existingOrder != null) ? existingOrder : new Order();

        order.setOrderDate(java.sql.Timestamp.valueOf(orderDTO.getOrderDate()));
        order.setSupplierOrder(orderDTO.isSupplierOrder());
        order.setStatus(Order.OrderStatus.valueOf(orderDTO.getStatus()));

        if (orderDTO.getCustomerId() != null) {
            Customer customer = new Customer();
            customer.setId(orderDTO.getCustomerId());
            order.setCustomer(customer);
        } else {
            order.setCustomer(null);
        }

        if (orderDTO.getSupplierId() != null) {
            Supplier supplier = new Supplier();
            supplier.setId(orderDTO.getSupplierId());
            order.setSupplier(supplier);
        } else {
            order.setSupplier(null);
        }

        // Lignes de commande
        if (orderDTO.getItems() != null) {
            order.setItems(orderDTO.getItems().stream()
                    .map(this::toOrderItem)
                    .collect(Collectors.toList()));
        }

        return order;
    }

    public OrderItem toOrderItem(OrderLineDTO orderLineDTO) {
        if (orderLineDTO == null) {
            return null;
        }

        OrderItem item = new OrderItem();
        item.setId(orderLineDTO.getId());
        item.setQuantity(orderLineDTO.getQuantity());
        return item;
    }
}
