package org.example.service;

import org.example.dao.UserStorage;
import org.example.entity.User;
import org.example.util.ConsoleWriter;
import org.example.validator.UserValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private final UserStorage userStorage;
    private final UserValidator userValidator;
    private final ConsoleWriter consoleWriter;

    public UserService(@Qualifier("SQLUserStorage") UserStorage userStorage, UserValidator userValidator, ConsoleWriter consoleWriter) {
        this.userStorage = userStorage;
        this.userValidator = userValidator;
        this.consoleWriter = consoleWriter;
    }

    public boolean save(String login, String name, String password) {
        if (!userValidator.loginAndNameValidate(login, name) || !userValidator.passwordValidate(password)) {
            consoleWriter.outputError("Illegal type of credos");
            return false;
        }
        if (getUserByLogin(login) != null) {
            consoleWriter.outputError("User with such login already exists");
            return false;
        }
        return userStorage.save(new User(login, name, password));
    }

    public List<User> findAll() {
        return userStorage.findAll();
    }

    public User getUserByLogin(String login) {
        return userStorage.getUserByLogin(login);
    }

    public boolean authByUsernameAndPassword(String username, String password) {
        if(getUserByLogin(username)==null){
            consoleWriter.outputError("Looks like you're not signed up");
            return false;
        }
        if (!userStorage.authByUsernameAndPassword(username, password)){
            consoleWriter.outputError("Wrong credos");
            return false;
        }
        return true;
    }
}
