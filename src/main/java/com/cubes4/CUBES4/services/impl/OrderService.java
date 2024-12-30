//package com.cubes4.CUBES4.services.impl;
//
//import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
//import com.cubes4.CUBES4.models.Order;
//import com.cubes4.CUBES4.repositories.OrderRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author Maël NOUVEL <br>
// * 12/2024
// **/
//@Service
//public class OrderService {
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    public List<Order> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public Order getOrderById(Long id) {
//        return orderRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
//    }
//
//    public List<Order> getOrdersByStatus(Order.OrderStatus status) {
//        return orderRepository.findByStatus(status);
//    }
//
//    public Order createOrder(Order order) {
//        return orderRepository.saveAndFlush(order);
//    }
//
//    public Order updateOrder(Long id, Order updatedOrder) {
//        return orderRepository.findById(id)
//                .map(order -> {
//                    order.setOrderDate(updatedOrder.getOrderDate());
//                    order.setSupplierOrder(updatedOrder.getSupplier() != null);
//                    order.setSupplier(updatedOrder.getSupplier());
//                    order.setOrderItems(updatedOrder.getOrderItems());
//                    order.setCustomer(updatedOrder.getCustomer());
//                    order.setStatus(updatedOrder.getStatus());
//                    return orderRepository.saveAndFlush(order);
//                }).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
//    }
//
//    public void deleteOrder(Long id) {
//        Order order = getOrderById(id);
//        orderRepository.delete(order);
//    }
//}
