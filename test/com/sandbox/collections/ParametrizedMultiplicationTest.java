package com.sandbox.collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by DKachurovskiy on 9/19/2014.
 */
@RunWith(Parameterized.class)
public class ParametrizedMultiplicationTest{

    private final int multiplier;

    public ParametrizedMultiplicationTest(int multiplier) {
        this.multiplier = multiplier;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 1 }, { 5 }, { 121 } };
        return Arrays.asList(data);
    }

    @Test
    public void testMultiplication() {
        System.out.println("Multiplication...");
        assertEquals(multiplier * multiplier, (int) Math.pow(multiplier, 2));
    }
}
