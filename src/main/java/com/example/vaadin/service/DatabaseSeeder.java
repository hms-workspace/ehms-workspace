package com.example.vaadin.service;

import com.example.vaadin.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder {

    private final UserService userService;

    public DatabaseSeeder(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void seedAdminUser() {
        userService.findByUsername("admin").orElseGet(() -> {
            AppUser admin = new AppUser("admin", "admin123", "ADMIN");
            return userService.save(admin);
        });
    }
