package org.example.entity;

import java.util.LinkedList;
import java.util.List;

public class User {
    private static Long counter = 0L;
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void addToStory(Operation operation){
        story.add(operation);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", story=" + story +
                '}';
    }
}
