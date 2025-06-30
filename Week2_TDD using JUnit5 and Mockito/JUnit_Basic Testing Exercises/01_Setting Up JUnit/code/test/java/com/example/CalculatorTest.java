package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3));
        System.out.println("Add Test Passed");

    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(2, calc.subtract(5, 3));
        System.out.println("Subtract Test Passed");

    }
}
