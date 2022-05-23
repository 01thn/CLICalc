package org.example.service;

import org.example.dao.OperationStorage;
import org.example.entity.Operation;
import org.example.entity.User;
import org.example.util.Math;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CalculatorService {

    private final OperationStorage operationStorage;

    public CalculatorService(@Qualifier("hibernateOperationStorage")
                             OperationStorage operationStorage) {
        this.operationStorage = operationStorage;
    }

    public Operation calc(double var1, double var2, String operationType, User user) {
        double result;
        Operation operation;
        switch (operationType) {
            case "add":
                result = Math.add(var1, var2);
                operation = new Operation(user, var1, var2, operationType, result);
                operationStorage.save(operation);
                return operation;
            case "sub":
                result = Math.sub(var1, var2);
                operation = new Operation(user, var1, var2, operationType, result);
                operationStorage.save(operation);
                return operation;
            case "mul":
                result = Math.mul(var1, var2);
                operation = new Operation(user, var1, var2, operationType, result);
                operationStorage.save(operation);
                return operation;
            case "div":
                result = Math.div(var1, var2);
                operation = new Operation(user, var1, var2, operationType, result);
                operationStorage.save(operation);
                return operation;
        }
        return null;
    }
}
