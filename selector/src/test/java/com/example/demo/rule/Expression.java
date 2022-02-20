package com.example.demo.rule;

import com.example.demo.enums.OperatorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Expression {
    private Integer x;
    private Integer y;
    private OperatorEnum operator;
}
