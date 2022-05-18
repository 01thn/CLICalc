package org.example.dao;

import org.example.entity.Operation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SQLOperationStorage implements OperationStorage {
    @Override
    public void save(Operation operation) {

    }

    @Override
    public List<Operation> findAllByUser(String username) {
        return null;
    }

    @Override
    public List<Operation> findAll() {
        return null;
    }
}
