package com.restaurant.server.repository;

import com.restaurant.server.model.Order;
import com.restaurant.server.model.DTO.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,  Long> {

    @Query("SELECT new com.restaurant.server.model.DTO.OrderDTO(o.id, o.amount, o.createdAt, c.name, oi.item.name, oi.itemsQuantity) FROM Order o JOIN o.client c JOIN o.orderItems oi")
    List<OrderDTO> findOrdersWithItemsAndClient();


}
