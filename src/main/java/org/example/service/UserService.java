package org.example.service;

import org.example.dao.UserStorage;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserStorage userStorage;

    public UserService(@Qualifier("SQLUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public boolean save(User user) {
        return userStorage.save(user);
    }

    public List<User> findAll() {
        return userStorage.findAll();
    }

    public User findByUsername(String username) {
        return userStorage.findByUsername(username);
    }

    public boolean authByUsernameAndPassword(String username, String password) {
        return userStorage.authByUsernameAndPassword(username, password);
    }
}
