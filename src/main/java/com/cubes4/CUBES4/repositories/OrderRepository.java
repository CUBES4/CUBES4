package com.cubes4.CUBES4.repositories;

import com.cubes4.CUBES4.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(Order.OrderStatus status);
}
