package org.example;

import org.example.util.Math;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {

    @Test
    public void useSumMethod() {
        double result = Math.add(3, 5);
        Assert.assertEquals(8L, (long) result);
    }

    @Test
    public void useMinusMethod() {
        double result = Math.sub(10, 4);
        Assert.assertEquals(6L, (long) result);
    }

    @Test
    public void useMultiplyMethod() {
        double result = Math.mul(5, 5);
        Assert.assertEquals(25L, (long) result);
    }

    @Test
    public void useDivideMethod() {
        double result = Math.div(45, 9);
        Assert.assertEquals(5L, (long) result);
    }
}
