package com.example.demo.lambda;

import com.example.demo.enums.OperatorEnum;
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


    @Test
    public void use(){
        System.out.println(new QueryGrantTypeService().getResult("add",1,2));
    }


}
