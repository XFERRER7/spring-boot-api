package com.restaurant.server.repository;

import com.restaurant.server.model.OrderItem;
import com.restaurant.server.model.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {

}
