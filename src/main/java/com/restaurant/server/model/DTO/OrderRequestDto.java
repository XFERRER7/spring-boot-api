package com.restaurant.server.model.DTO;

import java.util.List;

public class OrderRequestDto {

    private Long clientId;
    private List<OrderItemRequestDto> items;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<OrderItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDto> items) {
        this.items = items;
    }
}
