package com.restaurant.server.repository;

import com.restaurant.server.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);

}
