package com.example.demo.enums;

import org.junit.jupiter.api.Test;

/**
 * created with IntelliJ IDEA.
 *
 * @author: yz
 * @date: 2022/1/29
 * @time: 7:26 下午
 * @description:
 */
public class Calculator {
    public int calculate(int a, int b, OperatorEnum operator) {
        return operator.apply(a, b);
    }


    @Test
    public void use(){
        System.out.println(calculate(1,2,OperatorEnum.reversion(1)));
    }


}
