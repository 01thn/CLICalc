package org.example.storage;

import org.example.entity.User;
import org.example.entity.UserStorage;
import org.example.validator.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserStorage {
    private UserStorage userStorage;
    private UserValidator userValidator;

    public InMemoryUserStorage(UserStorage userStorage, UserValidator userValidator) {
        this.userStorage = userStorage;
        this.userValidator = userValidator;
    }

    public boolean userSignUp(String login, String name, String password) {
        boolean result = false;
        if (userValidator.passwordValidate(password)) {
            User bufUser = new User(login, name, password);
            userStorage.addToStorage(bufUser.getId(), bufUser);
            result = true;
        }
        return result;
    }
}
