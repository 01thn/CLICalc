package org.example.entity;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class User {
    private static long counter = 0L;
    private long id;
    private String login;
    private String name;
    private String password;
    private List<Operation> story;

    public User(String login, String name, String password) {
        counter++;
        this.id = counter;
        this.login = login;
        this.name = name;
        this.password = password;
        this.story = new LinkedList<>();
    }

}
