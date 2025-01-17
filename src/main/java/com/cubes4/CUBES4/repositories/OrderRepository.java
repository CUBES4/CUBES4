package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Order;
import com.cubes4.CUBES4.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Requête personnalisée pour récupérer les commandes avec leurs items
    @Query("SELECT o FROM Order o JOIN FETCH o.items")
    List<Order> findAllWithItems();

    List<Order> findByStatus(Order.OrderStatus status);
}
