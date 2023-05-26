package com.restaurant.server.service;

import com.restaurant.server.model.Item;
import com.restaurant.server.repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> createAllItems(List<Item> items) {
        return itemRepository.saveAll(items);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item updateItem(Item item) {
        Item existingItem = itemRepository.findById(item.getId()).orElse(null);
        existingItem.setName(item.getName());
        existingItem.setPrice(item.getPrice());
        existingItem.setDescription(item.getDescription());
        existingItem.setType(item.getType());
        existingItem.setQuantity(item.getQuantity());
        return itemRepository.save(existingItem);
    }

    public void removeOneFromQuantity(Long id, Integer quantity) {

        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem == null) {
            throw new RuntimeException("Item not found");
        }

        if (existingItem.getQuantity() < quantity) {
            throw new RuntimeException("Quantity not available");
        }

        Objects.requireNonNull(existingItem).setQuantity(existingItem.getQuantity() - quantity);

        itemRepository.save(existingItem);

    }

    public void deleteItem(Long id) {

        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem == null) {
            throw new RuntimeException("Item not found");
        }

        itemRepository.deleteById(id);
    }


}
