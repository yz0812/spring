package com.example.demo.rule;

import com.example.demo.enums.Operator;
import com.example.demo.enums.OperatorEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * created with IntelliJ IDEA.
 *
 * @author: yz
 * @date: 2022/1/30
 * @time: 12:35 上午
 * @description:
 */
public class Calculator {
    @Test
    public void whenNumbersGivenToRuleEngine_thenReturnCorrectResult() {
        Expression expression = new Expression(5, 5, OperatorEnum.ADD);
        RuleEngine engine = new RuleEngine();
        Result result = engine.process(expression);

        assertNotNull(result);
        assertEquals(10, result.getValue());
    }
}
