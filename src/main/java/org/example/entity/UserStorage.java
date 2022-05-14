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

    @Override
    public String toString() {
        return "UserStorage{" +
                "storage=" + storage +
                '}';
    }
}
