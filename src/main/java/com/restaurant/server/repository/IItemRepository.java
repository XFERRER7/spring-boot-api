package com.restaurant.server.repository;

import com.restaurant.server.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findById(Long id);

}
