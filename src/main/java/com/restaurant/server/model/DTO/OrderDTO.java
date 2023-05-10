package com.restaurant.server.model.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.restaurant.server.model.serializer.OrderDTOSerializer;

import java.util.ArrayList;
import java.util.List;

@JsonSerialize(using = OrderDTOSerializer.class)
public class OrderDTO {


    private Long id;
    private Integer amount;
    private String createdAt;
    private String clientName;
    private List<ItemDTO> items = new ArrayList<>();

    public OrderDTO(Long id ,Integer amount, String createdAt, String clientName, String itemName, Integer itemQuantity) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.clientName = clientName;
        this.items.add(new ItemDTO(itemName, itemQuantity));
    }

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
