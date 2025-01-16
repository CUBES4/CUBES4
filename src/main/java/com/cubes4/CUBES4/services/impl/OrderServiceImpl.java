package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.dto.OrderLineDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.OrderMapper;
import com.cubes4.CUBES4.models.Order;
import com.cubes4.CUBES4.models.OrderItem;
import com.cubes4.CUBES4.repositories.*;
import com.cubes4.CUBES4.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order -> {
            order.getItems().size();
            order.getCustomer();
            order.getSupplier();
        });

        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersWithNames() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> {
            OrderDTO dto = orderMapper.toDTO(order);
            if (order.isSupplierOrder()) {
                String supplierName = supplierRepository.findNameBySupplierId(order.getSupplier().getId());
                dto.setSupplierName(supplierName);
            } else {
                String customerFullName = customerRepository.findFullNameByCustomerId(order.getCustomer().getId());
                dto.setCustomerName(customerFullName);
            }
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable avec l'ID : " + id));
        return orderMapper.toDTO(order);
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO, null);
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(Long orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + orderId));
        Order updatedOrder = orderMapper.toEntity(orderDTO, existingOrder);
        orderRepository.save(updatedOrder);
        return existingOrder;
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable avec l'ID : " + id));
        orderRepository.delete(order);
    }

    @Override
    public List<String> getAllArticleNames() {
        return articleRepository.findAll().stream()
                .map(article -> article.getName())
                .collect(Collectors.toList());
    }

    @Override
    public void addOrderLine(Long orderId, OrderLineDTO orderLineDTO) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable avec l'ID : " + orderId));

        OrderItem newItem = new OrderItem();
        newItem.setQuantity(orderLineDTO.getQuantity());
        newItem.setArticle(articleRepository.findByName(orderLineDTO.getArticleName())
                .orElseThrow(() -> new ResourceNotFoundException("Article introuvable avec le nom : " + orderLineDTO.getArticleName())));
        newItem.setOrder(order);

        order.getItems().add(newItem);
        orderRepository.save(order);
    }

    @Override
    public void updateOrderLine(Long orderId, OrderLineDTO line) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable avec l'ID : " + orderId));
        OrderItem item = order.getItems().stream()
                .filter(i -> i.getArticle().getName().equals(line.getArticleName()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Article introuvable : " + line.getArticleName()));

        item.setQuantity(line.getQuantity());
        orderRepository.save(order);
    }
}
