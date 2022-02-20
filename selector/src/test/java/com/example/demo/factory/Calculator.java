package com.example.demo.factory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * created with IntelliJ IDEA.
 *
 * @author: yz
 * @date: 2022/1/28
 * @time: 6:13 下午
 * @description:
 */
public class Calculator {

    @Test
    public void use(){
        System.out.println(calculateUsingFactory(1,2,"add"));
    }

    public int calculateUsingFactory(int a, int b, String operator) {
        Operation targetOperation = OperatorFactory
                .getOperation(operator)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Operator"));
        return targetOperation.apply(a, b);
    }
}
