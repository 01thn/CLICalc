package org.example.model;

import org.example.service.Calculator;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Application {

    private ConsoleWriter cw;
    private ConsoleReader cr;

    public Application(ConsoleWriter cw, ConsoleReader cr) {
        this.cw = cw;
        this.cr = cr;
    }

    public void start() {
        boolean isActive = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi there!");
        while (isActive) {
            cw.output("Please, input first variable: ");
            double var1 = cr.getDouble();
            cw.output("Please, input second variable: ");
            double var2 = cr.getDouble();
            cw.output("What do you want to do?");
            cw.output("Input 1 - for sum\nInput 2 - for minus\nInput 3 - for multiply\nInput 4 - for divide");

            int answer = cr.getInt();
            double result;
            switch (answer) {
                case 1:
                    result = Calculator.sum(var1, var2);
                    cw.output(String.valueOf(result));
                    break;
                case 2:
                    result = Calculator.minus(var1, var2);
                    cw.output(String.valueOf(result));
                    break;
                case 3:
                    result = Calculator.multiply(var1, var2);
                    cw.output(String.valueOf(result));
                    break;
                case 4:
                    result = Calculator.divide(var1, var2);
                    cw.output("result is :" + String.valueOf(result));
                    break;
                default:
                    cw.output("Something went wrong. Try again");
                    continue;
            }
            cw.output("Do you want to continue?\nInput 0 for exit or any other number to continue");
            int userAnswer = cr.getInt();
            if (userAnswer == 0) isActive = false;
        }

        System.out.println("Bye!");
    }
}
