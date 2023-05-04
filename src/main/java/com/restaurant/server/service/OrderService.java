package com.restaurant.server.service;

import com.restaurant.server.model.*;
import com.restaurant.server.repository.IClientRepository;
import com.restaurant.server.repository.IItemRepository;
import com.restaurant.server.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final IOrderRepository orderRepository;
    private final ClientService clientService;
    private final ItemService itemService;



    public OrderService(IOrderRepository orderRepository, ClientService clientService, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.itemService = itemService;
    }

    public Order createOrder(Long clientId, List<OrderItemRequest> items) {

        List<Item> listItems = new ArrayList<>();

        items.forEach(orderItemRequest -> {
           Item item = itemService.getItemById(orderItemRequest.getItemId());

           if(item == null) {
               throw new RuntimeException("Item not found");
           }
           else {
               listItems.add(item);
           }
        });

        Client clientSaved = clientService.getClientById(clientId);
        Integer amount = listItems.stream().mapToInt(Item::getPrice).sum();

        if(clientSaved == null) {
            throw new RuntimeException("Client not found");
        }

        Order order = new Order();
        order.setAmount(amount);
        order.setCreatedAt(LocalDateTime.now().toString());
        order.setClient(clientSaved);
        order.setItems(listItems);

        Order orderSaved = orderRepository.save(order);

        System.out.println(orderSaved.getId());

        return null;


//        List<Item> itemList = new ArrayList<>();
//        Client client = clientService.getClientById(clientId);
//        Integer amount = itemList.stream().mapToInt(Item::getPrice).sum();
//        List<OrderItem> orderItemList = new ArrayList<>();
//        OrderItem orderItem = new OrderItem();
//
//        items.forEach(orderItemRequest -> {
//           Item item = itemService.getItemById(orderItemRequest.getItemId());
//           itemList.add(item);
//        });
//
//        items.forEach(orderItemRequest -> {
//            orderItem.setItem(itemService.getItemById(orderItemRequest.getItemId()));
//            orderItem.setQuantity(orderItemRequest.getQuantity());
//
//            orderItemList.add(orderItem);
//        });
//
//
//
//        Order order = new Order();
//
//        order.setClient(client);
//        order.setCreatedAt(LocalDateTime.now().toString());
//        order.setAmount(amount);
//        order.setItems(orderItemList);
//
//
//        return orderRepository.save(order);
    }
}
