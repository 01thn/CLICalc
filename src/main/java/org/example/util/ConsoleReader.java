package org.example.util;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Scanner;

@Component
public class ConsoleReader {
    private Scanner scanner;

    public ConsoleReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getWord() {
        return scanner.next();
    }

    public double getDouble() {
        return scanner.nextDouble();
    }

    public int getInt() {
        return scanner.nextInt();
    }

    @PreDestroy
    public void destroy() {
        scanner.close();
    }
}
