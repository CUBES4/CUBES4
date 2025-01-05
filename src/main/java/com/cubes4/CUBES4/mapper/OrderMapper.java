package com.cubes4.CUBES4.mapper;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.models.Order;
import com.cubes4.CUBES4.models.OrderItem;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(DATE_FORMAT.format(order.getOrderDate()));
        dto.setOrderType(order.isSupplierOrder() ? "Fournisseur" : "Client");
        dto.setStatus(order.getStatus().toString());
        dto.setProductName(order.getItems().stream()
                .map(item -> item.getArticle().getName())
                .collect(Collectors.joining(", ")));
        dto.setQuantity(order.getItems().stream()
                .mapToInt(OrderItem::getQuantity)
                .sum());
        dto.setTotalPrice(order.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getArticle().getUnitPrice())
                .sum());
        dto.setPaymentStatus(order.isSupplierOrder() ? "À payer" : "Crédité");
        dto.setItems(order.getItems().stream()
                .map(this::formatOrderItem)
                .collect(Collectors.toList()));
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        Order order = new Order();
        order.setOrderDate(dto.getOrderDateAsDate());
        order.setSupplierOrder("Fournisseur".equals(dto.getOrderType()));
        order.setStatus(Order.OrderStatus.valueOf(dto.getStatus()));
        // Les items ne sont pas directement mappés ici pour simplifier
        return order;
    }

    public void updateEntity(Order order, OrderDTO dto) {
        order.setOrderDate(dto.getOrderDateAsDate());
        order.setSupplierOrder("Fournisseur".equals(dto.getOrderType()));
        order.setStatus(Order.OrderStatus.valueOf(dto.getStatus()));
        // Les items ne sont pas directement mis à jour ici pour simplifier
    }

    private String formatOrderItem(OrderItem item) {
        return String.format("%s x %d @ %.2f€",
                item.getArticle().getName(),
                item.getQuantity(),
                item.getArticle().getUnitPrice());
    }
}
