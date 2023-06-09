package com.restaurant.server.controller;

import com.restaurant.server.model.Client;
import com.restaurant.server.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client newClient = clientService.createClient(client);
            return ResponseEntity.ok(newClient);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Client> authenticateClient(@RequestBody Client client) {
        try {
            Client response = clientService.authenticateClient(client.getEmail(), client.getPassword());

            if(response != null) {
                return ResponseEntity.ok().body(response);
            }
            else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
