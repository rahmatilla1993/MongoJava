package com.example.springbootadvanced.math;

import lombok.Getter;

@Getter
public class MathVarArgsSumSource {
    private int[] values;
    private int expected;

    public MathVarArgsSumSource(int expected, int... args) {
        this.expected = expected;
        this.values = args;
    }
}
