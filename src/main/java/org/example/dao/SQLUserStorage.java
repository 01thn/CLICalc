package org.example.dao;

import org.example.entity.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLUserStorage implements UserStorage {
    private Connection connection;

    public SQLUserStorage(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("insert into users (login, name, password) values(?,?,?)");
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
            ResultSet rs = statement.executeQuery("select * from users");
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
    public User findByUsername(String username) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where login=?");
            ps.setString(1, username);
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
    public boolean authByUsernameAndPassword(String username, String password) {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where login=? and password=?");
            ps.setString(1, username);
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
            PreparedStatement ps = connection.prepareStatement("select id from users where login=?");
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
            PreparedStatement ps = connection.prepareStatement("select * from users where id=?");
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
