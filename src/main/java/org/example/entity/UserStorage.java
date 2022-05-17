package org.example.entity;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserStorage {
    private Map<Long, User> storage;

    public UserStorage(Map<Long, User> storage) {
        this.storage = storage;
    }

    public void addToStorage(Long id, User user) {
        storage.put(id, user);
    }

    public boolean findUser(String login) {
        return storage.values().stream().map(User::getLogin).anyMatch(user -> user.equals(login));
    }

    public boolean authUser(String login, String password) {
        return storage.values().stream()
                .filter(user -> user.getLogin().equals(login))
                .anyMatch(user -> user.getPassword().equals(password));
    }

    public User getUser(String login) {
        return storage.values().stream().filter(user -> user.getLogin().equals(login)).findFirst().get();
    }

    @Override
    public String toString() {
        return "UserStorage{" +
                "storage=" + storage +
                '}';
    }
}
