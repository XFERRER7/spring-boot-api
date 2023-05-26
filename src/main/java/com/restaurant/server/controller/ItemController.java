package com.restaurant.server.controller;

import com.restaurant.server.model.Item;
import com.restaurant.server.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("item")
@CrossOrigin(origins = "*")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @PostMapping("create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {

        try {
            Item newItem = itemService.createItem(item);
            return ResponseEntity.ok().body(newItem);
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("get-all")
    public ResponseEntity<Iterable<Item>> getAllItems() {

        try {
            Iterable<Item> items = itemService.getAllItems();
            return ResponseEntity.ok().body(items);
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item updatedItem) {
        Item updated = itemService.updateItem(updatedItem);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }

}
