package com.example.demo.enums;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum OperatorEnum {

    ADD(1) {
        @Override
        public int apply(int a, int b) {
            return a + b;
        }
    },

    MULTIPLY(2) {
        @Override
        public int apply(int a, int b) {
            return a * b;
        }
    },

    SUBTRACT(3) {
        @Override
        public int apply(int a, int b) {
            return a - b;
        }
    },

    DIVIDE(4) {
        @Override
        public int apply(int a, int b) {
            return a / b;
        }
    },

    MODULO(5) {
        @Override
        public int apply(int a, int b) {
            return a % b;
        }
    };

    public abstract int apply(int a, int b);
    private final Integer operator;

    private static final Map<Integer, OperatorEnum> LOOKUP = Maps.uniqueIndex(
            Arrays.asList(OperatorEnum.values()),
            OperatorEnum::getOperator
    );
    @Nullable
    public static OperatorEnum reversion(int status) {
        return LOOKUP.get(status);
    }
}
