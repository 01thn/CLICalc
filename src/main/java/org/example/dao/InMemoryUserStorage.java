package org.example.dao;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorage implements UserStorage {

    private final Map<String, User> map = new HashMap<>();

    @Override
    public void save(User user) {
		map.put(user.getLogin(), user);
    }

    @Override
    public List<User> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public User findByUsername(String username) {
        if(map.containsKey(username)){
            return map.get(username);
        }
        return null;
    }

    @Override
    public boolean authByUsernameAndPassword(String username, String password) {
        User bufUser = map.get(username);
        return bufUser.getLogin().equals(username) && bufUser.getPassword().equals(password);
    }
}
