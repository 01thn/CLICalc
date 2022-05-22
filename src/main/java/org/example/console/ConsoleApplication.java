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
        int code = work(session);
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
                break;
            default:
                cw.outputError("Input error");
        }
        return session;
    }

    private boolean signUp() {
        cw.output("How can I call you?");
        String name = cr.getWord();
        cw.output("Nice! What do you wanna use as login?");
        String login = cr.getWord();
        cw.output("Choose a password for your account");
        String password = cr.getWord();
        return userService.save(login, name, password);
    }

    private User signIn() {
        cw.output("Input your login: ");
        String login = cr.getWord();
        cw.output("Input your password: ");
        String password = cr.getWord();
        if (userService.authByUsernameAndPassword(login, password)) {
            return userService.getUserByLogin(login);
        } else {
            return null;
        }
    }

    private int work(Session session) {
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
            opearationType = getOperationType(answer);
            if (opearationType == null) continue;
            operation = calculatorService.calc(var1, var2, opearationType, user);
            cw.output(String.valueOf(operation));
            cw.output("Do you want to continue?\nInput 0 for exit or any other number to continue");
            int userAnswer = cr.getInt();
            if (userAnswer == 0) isActive = false;
        }
        cw.output("Bye!");
        return 0;
    }

    private String getOperationType(int answer) {
        switch (answer) {
            case 1:
                return "add";
            case 2:
                return "sub";
            case 3:
                return "mul";
            case 4:
                return "div";
            default:
                cw.outputError("Something went wrong");
                return null;
        }
    }
}
