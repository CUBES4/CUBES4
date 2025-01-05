package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.models.Order;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    Order createOrder(OrderDTO orderDTO);
    Order updateOrder(Long orderId, OrderDTO orderDTO);
    void deleteOrder(Long id);
}
