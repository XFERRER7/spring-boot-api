package com.restaurant.server.service;

import com.restaurant.server.model.Client;
import com.restaurant.server.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {


    @Autowired
    private IClientRepository clientRepository;

    public Client createClient(Client user) throws Exception {
        if (clientRepository.findByEmail(user.getEmail()) != null) {
            throw new Exception("E-mail already registered!");
        }
        return clientRepository.save(user);
    }

    public Client authenticateClient(String email, String password) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && client.getPassword().equals(password)) {
            return client;
        } else {
            return null;
        }
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).get();
    }

}
