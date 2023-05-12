package com.restaurant.server.model.DTO;

import java.util.ArrayList;
import java.util.List;

public class OrderPdfDTO {

    private Long idOrder;
    private Integer amount;
    private String createdAt;
    private Integer itemsQuantity;

    public OrderPdfDTO(Long idOrder ,Integer amount, String createdAt, Integer itemsQuantity) {
        this.idOrder = idOrder;
        this.amount = amount;
        this.createdAt = createdAt;
        this.itemsQuantity = itemsQuantity;
    }

    public OrderPdfDTO() {
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getAmount() {
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

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }
}
