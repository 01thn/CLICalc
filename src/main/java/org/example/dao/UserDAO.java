package org.example.dao;

import org.springframework.stereotype.Component;

public interface UserDAO {
    boolean userSignUp(String login, String name, String password);
    boolean userExists(String login);
    boolean authUser(String login, String password);
}
