package org.example.validator;

import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public UserValidator() {
    }
    public boolean loginAndNameValidate(String login, String name) {
        return login.length() > 3 && name.length() > 3;
    }

    public boolean passwordValidate(String password) {
        String regex = "([a-zA-Z]|\\d){6,}";
        return password.matches(regex);
    }
}
