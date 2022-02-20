package com.example.demo.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * created with IntelliJ IDEA.
 *
 * @author: yz
 * @date: 2022/1/30
 * @time: 12:27 上午
 * @description:
 */
public class Calculator {
    public int calculate(Command command) {
        return command.execute();
    }

    @Test
    public void whenCalculateUsingCommand_thenReturnCorrectResult() {
        Calculator calculator = new Calculator();
        int result = calculator.calculate(new AddCommand(3, 7));
        assertEquals(10, result);
    }
}
