package org.example.model;

import java.util.Scanner;

public class Application {

    public void start() {
        boolean isActive = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi there!");
        while (isActive) {
            System.out.println("Please, input first variable: ");
            int var1 = scanner.nextInt();
            System.out.println("Please, input second variable: ");
            int var2 = scanner.nextInt();
            System.out.println("What do you want to do?");
            System.out.println("Input 1 - for sum\nInput 2 - for minus\nInput 3 - for multiply\nInput 4 - for divide");

            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    System.out.println(sum(var1, var2));
                    break;
                case 2:
                    System.out.println(minus(var1, var2));
                    break;
                case 3:
                    System.out.println(multiply(var1, var2));
                    break;
                case 4:
                    System.out.println(divide(var1, var2));
                    break;
                default:
                    System.out.println("Something went wrong. Try again");
                    continue;
            }
            System.out.println("Do you want to continue?\nInput 0 for exit or any other number to continue");
            int userAnswer = scanner.nextInt();
            if (userAnswer == 0) isActive = false;
        }

        System.out.println("Bye!");
    }

    private double sum(double var1, double var2) {
        return var1 + var2;
    }

    private double minus(double var1, double var2) {
        return var1 - var2;
    }

    private double multiply(double var1, double var2) {
        return var1 * var2;
    }

    private double divide(double var1, double var2) {
        return var1 / var2;
    }
}
