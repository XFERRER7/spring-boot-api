package com.restaurant.server.controller;

import com.restaurant.server.model.Item;
import com.restaurant.server.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @PostMapping("create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {

        try {
            Item newItem = itemService.createItem(item);
            return ResponseEntity.ok().body(item);
        }
        catch(Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
