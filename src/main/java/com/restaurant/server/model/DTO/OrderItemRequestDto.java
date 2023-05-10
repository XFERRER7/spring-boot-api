package com.restaurant.server.model.DTO;

public class OrderItemRequestDto {
    private Long itemId;
    private Integer quantity;

    public OrderItemRequestDto() {
    }

    public OrderItemRequestDto(Long itemId, Integer quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
