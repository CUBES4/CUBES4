package com.cubes4.CUBES4.services.impl;

import com.cubes4.CUBES4.dto.OrderDTO;
import com.cubes4.CUBES4.dto.OrderLineDTO;
import com.cubes4.CUBES4.exceptions.ResourceNotFoundException;
import com.cubes4.CUBES4.mapper.OrderMapper;
import com.cubes4.CUBES4.models.Order;
import com.cubes4.CUBES4.models.OrderItem;
import com.cubes4.CUBES4.repositories.OrderRepository;
import com.cubes4.CUBES4.services.OrderService;
import com.cubes4.CUBES4.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        // Forcer le chargement des relations
        orders.forEach(order -> {
            order.getItems().size(); // Initialisation explicite des items
            order.getCustomer();    // Initialisation explicite du client
            order.getSupplier();    // Initialisation explicite du fournisseur
        });

        return orders.stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
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
        // Rechercher la commande
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable avec l'ID : " + orderId));

        // Créer une nouvelle ligne de commande
        OrderItem newItem = new OrderItem();
        newItem.setQuantity(orderLineDTO.getQuantity());
        newItem.setArticle(articleRepository.findByName(orderLineDTO.getArticleName())
                .orElseThrow(() -> new ResourceNotFoundException("Article introuvable avec le nom : " + orderLineDTO.getArticleName())));
        newItem.setOrder(order);

        // Ajouter la ligne à la commande existante
        order.getItems().add(newItem);

        // Sauvegarder la commande mise à jour
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
