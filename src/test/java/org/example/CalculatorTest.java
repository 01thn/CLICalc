package org.example;

import org.example.service.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void useSumMethod() {
        double result = Calculator.sum(3, 5);
        Assert.assertEquals(8L, (long) result);
    }

    @Test
    public void useMinusMethod() {
        double result = Calculator.minus(10, 4);
        Assert.assertEquals(6L, (long) result);
    }

    @Test
    public void useMultiplyMethod() {
        double result = Calculator.multiply(5, 5);
        Assert.assertEquals(25L, (long) result);
    }

    @Test
    public void useDivideMethod() {
        double result = Calculator.divide(45, 9);
        Assert.assertEquals(5L, (long) result);
    }
}
