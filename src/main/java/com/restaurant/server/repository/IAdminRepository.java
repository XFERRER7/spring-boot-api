package com.restaurant.server.repository;

import com.restaurant.server.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Long> {

    Admin findByEmail(String email);

}
