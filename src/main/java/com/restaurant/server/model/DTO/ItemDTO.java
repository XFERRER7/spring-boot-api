package com.restaurant.server.model.DTO;

public class ItemDTO {

    private String name;
    private Integer quantity;

    public ItemDTO(String itemName, Integer itemQuantity) {
        this.name = itemName;
        this.quantity = itemQuantity;
    }

    public ItemDTO() {
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    public void setQuantity(Integer itemQuantity) {
        this.quantity = itemQuantity;
    }

}
