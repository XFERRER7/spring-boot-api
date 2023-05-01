package com.restaurant.server.controller;

import com.restaurant.server.model.Admin;
import com.restaurant.server.model.Client;
import com.restaurant.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        try {
            Admin newAdmin = adminService.createAdmin(admin);
            return ResponseEntity.ok(newAdmin);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAdmin(@RequestBody Admin admin) {
        try {
            boolean response = adminService.authenticateAdmin(admin.getEmail(), admin.getPassword());

            if(response) {
                return ResponseEntity.ok().body("{\"message\": \"Authenticated\"}");
            }
            else {
                return ResponseEntity.badRequest().body("{\"message\": \"Admin not found\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
