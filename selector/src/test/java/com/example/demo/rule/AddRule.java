package com.example.demo.rule;

import com.example.demo.enums.OperatorEnum;

public class AddRule implements Rule {

    private int result;

    @Override
    public boolean evaluate(Expression expression) {
        boolean evalResult = false;
        if (expression.getOperator() == OperatorEnum.ADD) {
            this.result = expression.getX() + expression.getY();
            evalResult = true;
        }
        return evalResult;
    }

    @Override
    public Result getResult() {
        return new Result(result);
    }
}
