package org.example.dao;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQLUserStorage implements UserStorage {
	@Override
	public void save(User user) {

	}

	@Override
	public List<User> findAll() {
		return null;
	}

	@Override
	public User findByUsername(String username) {
		return null;
	}

	@Override
	public boolean authByUsernameAndPassword(String username, String password) {
		return false;
	}
}
