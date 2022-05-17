package org.example.entity;

public class Session {
    private final User user;

    public Session(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Session{" +
                "user=" + user +
                '}';
    }
}
