package com.restaurant.server.service;

import com.restaurant.server.model.*;
import com.restaurant.server.model.DTO.ItemDTO;
import com.restaurant.server.model.DTO.OrderDTO;
import com.restaurant.server.model.DTO.OrderItemRequestDto;
import com.restaurant.server.model.DTO.OrderPdfDTO;
import com.restaurant.server.repository.IOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final IOrderRepository orderRepository;
    private final ClientService clientService;
    private final ItemService itemService;
    private final OrderItemService orderItemService;


    public OrderService(IOrderRepository orderRepository, ClientService clientService, ItemService itemService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.itemService = itemService;
        this.orderItemService = orderItemService;
    }

    @Transactional
    public Order createOrder(Long clientId, List<OrderItemRequestDto> items) {

        //Busca e salva os itens em uma lista
        List<Item> listItems = new ArrayList<>();

        items.forEach(orderItemRequest -> {
            Item item = itemService.getItemById(orderItemRequest.getItemId());

            if (item == null) {
                throw new RuntimeException("Item not found");
            } else {
                listItems.add(item);
            }
        });

        //Busca e salva o cliente em uma variavel
        Client clientSaved = clientService.getClientById(clientId);

        if (clientSaved == null) {
            throw new RuntimeException("Client not found");
        }


        //Calcula o valor total do pedido
        Integer amount = 0;
        for (Item item : listItems) {
            amount += item.getPrice() * items.stream().filter(orderItemRequest -> orderItemRequest.getItemId().equals(item.getId())).findFirst().orElseThrow(() -> new RuntimeException("Order item not found")).getQuantity();
        }


        Order orderSaved = createRawOrder(clientSaved, amount);

        List<OrderItem> orderItemsSaved = new ArrayList<>();
        for (Item item : listItems) {

            int quantity = items.stream().filter(orderItemRequest -> orderItemRequest.getItemId().equals(item.getId())).findFirst().orElseThrow(() -> new RuntimeException("Order item not found")).getQuantity();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(orderSaved);
            orderItem.setItem(item);
            orderItem.setItemsQuantity(quantity);

            Item itemToRemoveQuantity = itemService.getItemById(item.getId());
            itemService.removeOneFromQuantity(itemToRemoveQuantity.getId(), orderItem.getItemsQuantity());

            OrderItem orderItemSaved = orderItemService.create(orderItem);
            orderItemsSaved.add(orderItemSaved);
        }

        for (OrderItem orderItem : orderItemsSaved) {
            orderSaved.getOrderItems().add(orderItem);

            for (Item item : listItems) {
                item.getOrderItems().add(orderItem);
            }

        }

        Order orderFinal = orderRepository.save(orderSaved);

        itemService.createAllItems(listItems);

        return orderFinal;

    }

    @Transactional
    public Order createRawOrder(Client client, Integer amount) {

        Order order = new Order();
        order.setAmount(amount);
        order.setCreatedAt(LocalDateTime.now().toString());
        order.setClient(client);

        return orderRepository.save(order);
    }

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setAmount(order.getAmount());
            orderDTO.setCreatedAt(order.getCreatedAt());
            orderDTO.setClientName(order.getClient().getName());

            List<ItemDTO> itemDTOs = new ArrayList<>();
            for (OrderItem orderItem : order.getOrderItems()) {

                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setName(orderItem.getItem().getName());
                itemDTO.setQuantity(orderItem.getItemsQuantity());

                itemDTOs.add(itemDTO);
            }
            orderDTO.setItems(itemDTOs);

            orderDTOs.add(orderDTO);
        }

        return orderDTOs;
    }

    public List<OrderPdfDTO> getReport() {

        List<Order> allOrders = orderRepository.findAll();
        List<OrderPdfDTO> ordersPdf = new ArrayList<>();

        for (Order order : allOrders) {

            OrderPdfDTO orderPdfDTO = new OrderPdfDTO();

            orderPdfDTO.setIdOrder(order.getId());

            orderPdfDTO.setAmount(order.getAmount() / 100);

            String createdAt = order.getCreatedAt();
            String[] firstSplit = createdAt.split("T");
            String[] secondtSplit = firstSplit[0].split("-");
            String createdAtFinal = secondtSplit[2] + "/" + secondtSplit[1] + "/" + secondtSplit[0];
            orderPdfDTO.setCreatedAt(createdAtFinal);

            for (OrderItem orderItem : order.getOrderItems()) {

                Integer quantity = orderItem.getItemsQuantity();

                if (orderPdfDTO.getItemsQuantity() != null) {
                    orderPdfDTO.setItemsQuantity(orderPdfDTO.getItemsQuantity() + quantity);
                }
                else {
                    orderPdfDTO.setItemsQuantity(quantity);
                }

            }

            ordersPdf.add(orderPdfDTO);
        }

        return ordersPdf;

    }
}
