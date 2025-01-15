package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.dto.OrderLineDTO;
import com.cubes4.CUBES4.models.Order;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getAllOrdersWithNames();

    OrderDTO getOrderById(Long id);
    void addOrderLine(Long orderId, OrderLineDTO orderLineDTO);

    Order createOrder(OrderDTO orderDTO);
    Order updateOrder(Long orderId, OrderDTO orderDTO);
    void deleteOrder(Long id);
    void updateOrderLine(Long orderId, OrderLineDTO line);

    List<String> getAllArticleNames();
}
