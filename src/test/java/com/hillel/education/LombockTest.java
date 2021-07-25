package com.hillel.education;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LombockTest {

    private static TestClass testClass;

    @BeforeEach // BeforeAll
    void setUp() {
        testClass = new TestClass();
    }

    @Test
    void testLombockAnnotation() {
        testClass.setDelta(10);

        Assertions.assertEquals(10, testClass.getDelta());
    }

    @Test
    void test() {
        int givenA = 5;
        int givenB = 9;
        int expectedResult = 14;

        int sum = testClass.plus(5, 9);

        Assertions.assertEquals(expectedResult, sum);
    }

    @Setter
    @Getter
    static class TestClass {
        int delta = 0;

        int plus(int a, int b) {
            return a + b + delta;
        }
    }
}
