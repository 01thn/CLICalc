package org.example.config;

import org.example.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "org.example.entity")
@ComponentScan(basePackages = "org.example.storage")
@ComponentScan(basePackages = "org.example.validator")
public class RootConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public HashMap<Long, User> storage() {
        return new HashMap<>();
    }
}
