package com.restaurant.server.controller;

import com.restaurant.server.model.*;
import com.restaurant.server.model.DTO.OrderDTO;
import com.restaurant.server.model.DTO.OrderRequestDto;
import com.restaurant.server.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDto orderRequest) {

        try {
            Order order = orderService.createOrder(orderRequest.getClientId(), orderRequest.getItems());
            return ResponseEntity.ok("{\"message\": \"Order created\"}");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping("get-all")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

}
