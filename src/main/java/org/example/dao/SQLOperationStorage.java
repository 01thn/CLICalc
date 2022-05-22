package org.example.dao;

import org.example.entity.Operation;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SQLOperationStorage implements OperationStorage {
    private Connection connection;
    private SQLUserStorage sqlUserStorage;
    private final String SAVE_USER = "insert into operation (id_user, var1, var2, operation, result) values (?, ?, ?, ?, ?)";
    private final String FIND_OPERATION_BY_USER_ID = "select * from operation where id_user=?";
    private final String GET_ALL_OPERATIONS = "select * from operation";

    public SQLOperationStorage(Connection connection, SQLUserStorage sqlUserStorage) {
        this.connection = connection;
        this.sqlUserStorage = sqlUserStorage;
    }

    @Override
    public boolean save(Operation operation) {
        try {
            PreparedStatement ps = connection.prepareStatement(SAVE_USER);
            ps.setLong(1, operation.getUser().getId());
            ps.setDouble(2, operation.getVar1());
            ps.setDouble(3, operation.getVar2());
            ps.setString(4, operation.getOperation());
            ps.setDouble(5, operation.getResult());
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Operation> findAllByUser(String username) {
        ArrayList<Operation> operations = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_OPERATION_BY_USER_ID);
            ps.setLong(1, sqlUserStorage.getUserIdByLogin(username));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                operations.add(new Operation(
                        rs.getLong(1),
                        sqlUserStorage.getUserById(rs.getLong(2)),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getDouble(6))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return operations;
    }

    @Override
    public List<Operation> findAll() {
        ArrayList<Operation> operations = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_OPERATIONS);
            while (rs.next()) {
                operations.add(new Operation(rs.getLong(1),
                        sqlUserStorage.getUserById(rs.getLong(2)),
                        rs.getDouble(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getDouble(6))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return operations;
    }
}
