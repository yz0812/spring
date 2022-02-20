package com.example.demo.factory;

public class Reduce implements Operation {
    @Override
    public int apply(int a, int b) {
        return a - b;
    }
}
