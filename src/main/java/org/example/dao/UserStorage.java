package org.example.dao;

import org.example.entity.User;

import java.util.List;

public interface UserStorage {
    boolean save(User user);

    List<User> findAll();

    User findByUsername(String username);

    boolean authByUsernameAndPassword(String username, String password);
}
