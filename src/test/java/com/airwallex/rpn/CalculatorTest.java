package com.airwallex.rpn;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This test class includes all examples provided in the requirement.
 */
public class CalculatorTest {

    private Calculator calculator;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void testExample1() {
        calculator.processLine("5 2");
        verifyStack("5 2");
    }

    @Test
    public void shouldIgnoreMultipleSpaces() {
        calculator.processLine("5       2");
        verifyStack("5 2");
    }

    @Test
    public void shouldThrowErrorIfNumberInvalidOrUnknownOperator() {
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("invalid number or unknown operator: 'a'");

        calculator.processLine("a");
        verifyStack("");
    }

    @Test
    public void testExample2() {
        calculator.processLine("2 sqrt");
        verifyStack("1.4142135623");

        calculator.processLine("clear 9 sqrt");
        verifyStack("3");
    }

    @Test
    public void testExample3() {
        calculator.processLine("5 2 -");
        verifyStack("3");

        calculator.processLine("3 -");
        verifyStack("0");

        calculator.processLine("clear");
        verifyStack("");
    }

    @Test
    public void testExample4() {
        calculator.processLine("5 4 3 2");
        verifyStack("5 4 3 2");

        calculator.processLine("undo undo *");
        verifyStack("20");

        calculator.processLine("5 *");
        verifyStack("100");

        calculator.processLine("undo");
        verifyStack("20 5");
    }

    @Test
    public void testExample5() {
        calculator.processLine("7 12 2 /");
        verifyStack("7 6");

        calculator.processLine("*");
        verifyStack("42");

        calculator.processLine("4 /");
        verifyStack("10.5");
    }

    @Test
    public void testExample6() {
        calculator.processLine("1 2 3 4 5");
        verifyStack("1 2 3 4 5");

        calculator.processLine("*");
        verifyStack("1 2 3 20");

        calculator.processLine("clear 3 4 -");
        verifyStack("-1");
    }

    @Test
    public void testExample7() {
        calculator.processLine("1 2 3 4 5");
        verifyStack("1 2 3 4 5");

        calculator.processLine("* * * *");
        verifyStack("120");
    }

    @Test
    public void testExample8() {
        try {
            calculator.processLine("1 2 3 * 5 + * * 6 5");
            fail("should throw exception");
        } catch (InsufficientParameterException e) {
            assertEquals("operator * (position: 15): insufficient parameters", e.getMessage());
        }
        verifyStack("11");
    }

    private void verifyStack(String expectedNumbers) {
        assertEquals(expectedNumbers, calculator.getStackMessage());
    }
}
