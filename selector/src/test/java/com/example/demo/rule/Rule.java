package com.example.demo.rule;

public interface Rule {
    boolean evaluate(Expression expression);
    Result getResult();
}
