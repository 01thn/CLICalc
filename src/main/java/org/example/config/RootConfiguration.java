package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "org.example")
public class RootConfiguration {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/clicalc";
    private static String DB_USER = "postgres";
    private static String DB_PASS = "postgres";

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public Connection connection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
