package com.restaurant.server.repository;

import com.restaurant.server.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,  Long> {

}
