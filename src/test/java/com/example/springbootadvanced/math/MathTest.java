package com.example.springbootadvanced.math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathTest {

    private Math math;

    @BeforeEach
    void setUp() {
        math = new Math();
    }

    @Test
    void sub() {
        assertEquals(10, math.sub(25, 15));
        assertEquals(-1, math.sub(9, 10));
        assertEquals(3, math.sub(13, 10));
        assertEquals(6, math.sub(31, 25));
    }

    @ParameterizedTest
    @MethodSource({"mathVarArgsSumSourceStream"})
    void sum(MathVarArgsSumSource source) {
        assertEquals(source.getExpected(), math.sum(source.getValues()));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 10, 5, 8, 12, 3})
    void add(int item) {
        int expected = item + item;
        int actual = math.add(item, item);
        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "[{index}] {argumentsWithNames}")
    @CsvSource(useHeadersInDisplayName = true, value = {
            "divisor, divider, expected",
            "10, 5, 2",
            "20, 4, 5",
            "16, 2, 8",
            "21, 3, 7"
    }
    )
    void div(int a, int b, int expected) {
        assertEquals(expected, math.div(a, b));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"})
    void mul(int a, int b, int expected) {
        assertEquals(expected, math.mul(a, b));
    }

    @ParameterizedTest
    @MethodSource({"mathPowSources"})
    void pow(MathPowSource source) {
        assertEquals(source.getExpected(), math.pow(source.getNumber(), source.getPow()));
    }

    static List<MathPowSource> mathPowSources() {
        return List.of(
                new MathPowSource(2, 4, 16),
                new MathPowSource(3, 3, 27),
                new MathPowSource(4, 4, 256),
                new MathPowSource(5, 3, 125)
        );
    }

    static Stream<MathVarArgsSumSource> mathVarArgsSumSourceStream() {
        return Stream.of(
                new MathVarArgsSumSource(15, 1, 2, 3, 4, 5),
                new MathVarArgsSumSource(55, 1, 4, 9, 16, 25),
                new MathVarArgsSumSource(28, 2, 3, 4, 9, 10)
        );
    }
}