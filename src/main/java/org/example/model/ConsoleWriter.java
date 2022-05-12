package org.example.model;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter {

    public ConsoleWriter() {
    }

    public void output(String message) {
        System.out.println(message);
    }
}
