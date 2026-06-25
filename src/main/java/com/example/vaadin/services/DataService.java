package com.example.vaadin.services;

import org.springframework.stereotype.Service;

/**
 * Sample Service class
 * 
 * Example service demonstrating Spring dependency injection
 */
@Service
public class DataService {

    public String getApplicationInfo() {
        return "Vaadin Application Template v1.0.0";
    }

    public String getWelcomeMessage() {
        return "Welcome to Vaadin with Spring Boot!";
    }
}
