package org.example.console;

import lombok.AllArgsConstructor;
import org.example.entity.Operation;
import org.example.entity.Session;
import org.example.entity.User;
import org.example.service.CalculatorService;
import org.example.service.UserService;
import org.example.util.ConsoleReader;
import org.example.util.ConsoleWriter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsoleApplication implements Application {
    private final ConsoleWriter cw;
    private final ConsoleReader cr;
    private final UserService userService;
    private final CalculatorService calculatorService;

    public void start() {
        cw.output("Hi there!");
        Session session;
        do {
            session = startMenu();
        } while (session == null);
        int code = calculate(session);
        cw.output("Application was stopped with code " + code);
    }

    private Session startMenu() {
        Session session = null;
        cw.startMenu();
        switch (cr.getInt()) {
            case 1:
                while (!signUp()) {
                }
                cw.output("Congrats! You're successfully signed up");
                break;
            case 2:
                User user = signIn();
                if (user == null) {
                    break;
                }
                session = new Session(user);
        }
        return session;
    }

    private boolean signUp() {
        cw.output("How can I call you?");
        String name = cr.getWord();
        cw.output("Nice! What do you wanna use as login?");
        String login = cr.getWord();
        if (userService.findByUsername(login) != null) {
            cw.outputError("User with such login exists");
            return false;
        }
        cw.output("Choose a password for your account");
        String password = cr.getWord();
        userService.save(new User(login, name, password));
        return true;
    }

    private User signIn() {
        cw.output("Input your login: ");
        String login = cr.getWord();
        User user = userService.findByUsername(login);
        if (user == null) {
            cw.outputError("Looks like you aren't signed up");
            return null;
        }
        cw.output("Input your password: ");
        String password = cr.getWord();
        if (!userService.authByUsernameAndPassword(login, password)) {
            cw.outputError("Wrong credos");
            return null;
        }
        return user;
    }

    private int calculate(Session session) {
        boolean isActive = true;
        User user = session.getUser();
        Operation operation;
        String opearationType;
        while (isActive) {
            cw.output("Please, input first variable: ");
            double var1 = cr.getDouble();
            cw.output("Please, input second variable: ");
            double var2 = cr.getDouble();
            cw.output("What do you want to do?");
            cw.output("Input 1 - for add\nInput 2 - for sub\nInput 3 - for mul\nInput 4 - for div");
            int answer = cr.getInt();
            switch (answer) {
                case 1:
                    opearationType = "add";
                    break;
                case 2:
                    opearationType = "sub";
                    break;
                case 3:
                    opearationType = "mul";
                    break;
                case 4:
                    opearationType = "div";
                    break;
                default:
                    System.out.println("Something went wrong");
                    continue;
            }
            operation = calculatorService.calc(var1, var2, opearationType, user);
            System.out.println(operation);
            cw.output("Do you want to continue?\nInput 0 for exit or any other number to continue");
            int userAnswer = cr.getInt();
            if (userAnswer == 0) isActive = false;
        }
        System.out.println("Bye!");
        return 0;
    }
}
