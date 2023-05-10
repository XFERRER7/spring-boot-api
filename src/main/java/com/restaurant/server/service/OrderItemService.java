package com.restaurant.server.service;

import com.restaurant.server.model.OrderItem;
import com.restaurant.server.repository.IOrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final IOrderItemRepository orderItemRepository;

    public OrderItemService(IOrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> createAll(List<OrderItem> orderItems) {
        return orderItemRepository.saveAll(orderItems);
    }

    public OrderItem create(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

}
