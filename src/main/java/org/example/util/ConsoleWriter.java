package org.example.util;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter {

    public ConsoleWriter() {
    }

    public void output(String message) {
        System.out.println(message);
    }

    public void outputResult(double result) {
        System.out.println("The result is: " + result);
    }

    public void startMenu(){
        System.out.println("""
                <-- MENU -->
                Input 1 to create a new account
                Input 2 to sign in
                """);
    }

    public void outputError(String message){
        System.err.println(message);
    }
}
