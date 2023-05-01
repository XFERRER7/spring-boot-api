package com.restaurant.server.repository;

import com.restaurant.server.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {

}
