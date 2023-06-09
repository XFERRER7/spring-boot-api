package com.restaurant.server.controller;

import com.restaurant.server.model.Admin;
import com.restaurant.server.model.Client;
import com.restaurant.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<Admin> authenticateAdmin(@RequestBody Admin admin) {
        try {
            Admin response = adminService.authenticateAdmin(admin.getEmail(), admin.getPassword());

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
