package org.example.dao;

import org.example.entity.Operation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryOperationStorage implements OperationStorage {

    private final Map<String, List<Operation>> map = new HashMap<>();

    @Override
    public void save(Operation operation) {
        if (map.containsKey(operation.getUser().getLogin())) {
            List<Operation> operations = map.get(operation.getUser().getLogin());
            operations.add(operation);
        } else {
            map.put(operation.getUser().getLogin(), new ArrayList<>());
            List<Operation> operations = map.get(operation.getUser().getLogin());
            operations.add(operation);
        }
    }

    @Override
    public List<Operation> findAllByUser(String username) {
        return new ArrayList<>(map.get(username));
    }

    @Override
    public List<Operation> findAll() {
        List<Operation> operations = new ArrayList<>();
        map.values().forEach(operation->operations.add((Operation) operation));
        return operations;
    }
}
