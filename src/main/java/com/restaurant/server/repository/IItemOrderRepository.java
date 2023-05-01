package com.restaurant.server.repository;

import com.restaurant.server.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemOrderRepository extends JpaRepository<OrderItem, Long> {
}
