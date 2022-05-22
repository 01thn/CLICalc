package org.example.dao;

import org.example.entity.User;

import java.util.List;

public interface UserStorage {
    boolean save(User user);

    List<User> findAll();

    User getUserByLogin(String login);

    boolean authByUsernameAndPassword(String username, String password);
}
