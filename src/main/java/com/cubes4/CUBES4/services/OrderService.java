package com.cubes4.CUBES4.services;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.models.Order;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 02/2025
 **/
public interface OrderService {

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getOrdersByStatus(Order.OrderStatus status);

    OrderDTO createOrder(OrderDTO order);

    OrderDTO updateOrder(Long id, OrderDTO updatedOrder);

    void deleteOrder(Long id);
}
