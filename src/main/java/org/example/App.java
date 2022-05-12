package org.example;

import org.example.config.RootConfiguration;
import org.example.model.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext appctx = new AnnotationConfigApplicationContext(RootConfiguration.class);
        Application app = (Application) appctx.getBean("application");
        app.start();
    }
}
