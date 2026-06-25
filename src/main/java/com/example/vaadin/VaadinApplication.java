package com.example.vaadin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Vaadin Spring Boot Application
 * 
 * This application demonstrates a complete Vaadin setup with:
 * - Spring Boot integration
 * - Vaadin 24.0.0 UI framework
 * - Navigation and routing
 * - Responsive design
 * - Custom theming
 */
@SpringBootApplication
public class VaadinApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaadinApplication.class, args);
    }
}
