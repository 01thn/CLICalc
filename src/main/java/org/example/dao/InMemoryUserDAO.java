package org.example.dao;

import org.example.entity.User;
import org.example.entity.UserStorage;
import org.example.validator.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class InMemoryUserDAO implements UserDAO {
    private UserStorage userStorage;
    private UserValidator userValidator;

    public InMemoryUserDAO(UserStorage userStorage, UserValidator userValidator) {
        this.userStorage = userStorage;
        this.userValidator = userValidator;
    }

    public boolean userSignUp(String login, String name, String password) {
        boolean result = false;
        if (userValidator.passwordValidate(password) && userValidator.loginAndNameValidate(login, name)) {
            User bufUser = new User(login, name, password);
            userStorage.addToStorage(bufUser.getId(), bufUser);
            result = true;
        }
        return result;
    }

    public boolean userExists(String login) {
        return userStorage.findUser(login);
    }

    public User getUser(String login) {
        return userStorage.getUser(login);
    }

    public boolean authUser(String login, String password) {
        return userStorage.authUser(login, password);
    }
}
