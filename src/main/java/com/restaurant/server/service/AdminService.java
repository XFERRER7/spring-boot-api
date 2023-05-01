package com.restaurant.server.service;

import com.restaurant.server.model.Admin;
import com.restaurant.server.model.Client;
import com.restaurant.server.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private IAdminRepository iAdminRepository;

    public Admin createAdmin(Admin admin) throws Exception {
        if (iAdminRepository.findByEmail(admin.getEmail()) != null) {
            throw new Exception("E-mail already registered!");
        }
        return iAdminRepository.save(admin);
    }

    public boolean authenticateAdmin(String email, String password) {
        Admin admin = iAdminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

}
