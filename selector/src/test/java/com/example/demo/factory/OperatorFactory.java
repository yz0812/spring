package com.example.demo.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put("add", new Addition());
        operationMap.put("reduce", new Reduce());
        // more operators
    }

    public static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}
