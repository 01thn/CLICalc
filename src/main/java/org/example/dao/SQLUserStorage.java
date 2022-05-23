package org.example.dao;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLUserStorage implements UserStorage {
    private Connection connection;
    private final String SAVE_USER = "insert into users (login, name, password) values(?,?,?)";
    private final String GET_ALL_USERS = "select * from users";
    private final String GET_USER_BY_LOGIN = "select * from users where login=?";
    private final String GET_USER_BY_LOGIN_AND_PASS = "select * from users where login=? and password=?";
    private final String GET_USER_ID_BY_LOGIN = "select id from users where login=?";
    private final String GET_USER_BY_ID = "select * from users where id=?";

    public SQLUserStorage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_USERS);
            while (rs.next()) {
                users.add(new User(rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_LOGIN);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean authByUsernameAndPassword(String login, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_LOGIN_AND_PASS);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Long getUserIdByLogin(String login) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_ID_BY_LOGIN);
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public User getUserById(Long id) {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_USER_BY_ID);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
