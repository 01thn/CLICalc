package org.example;

import org.example.config.RootConfiguration;
import org.example.console.ConsoleApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext appctx = new AnnotationConfigApplicationContext(RootConfiguration.class);
        ConsoleApplication app = (ConsoleApplication) appctx.getBean("consoleApplication");
        app.start();
    }
}
