package org.example.config;

import org.example.model.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfiguration {

    @Bean
    public Application application() {
        return new Application();
    }

}
