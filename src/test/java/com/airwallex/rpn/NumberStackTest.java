package com.airwallex.rpn;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class NumberStackTest {

    @Test
    public void popAfterPush() {
        NumberStack stack = new NumberStack();
        stack.push(BigDecimal.ONE);
        stack.push(BigDecimal.TEN);
        assertSame(BigDecimal.TEN, stack.pop());
        assertSame(BigDecimal.ONE, stack.pop());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotAllowNullElement() {
        NumberStack stack = new NumberStack();
        stack.push(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void popShouldFailIfStackIsEmpty() {
        NumberStack stack = new NumberStack();
        stack.pop();
    }

    @Test
    public void testClearStack() {
        NumberStack stack = new NumberStack();
        stack.push(BigDecimal.ONE);
        assertFalse("stack should not be empty", stack.isEmpty());
        stack.clear();
        assertTrue("stack should be empty after cleared", stack.isEmpty());
    }

    @Test
    public void testCopyStack() {
        NumberStack stack1 = new NumberStack();
        stack1.push(BigDecimal.ONE);

        NumberStack stack2 = new NumberStack();
        stack2.push(BigDecimal.ZERO);
        stack2.push(BigDecimal.TEN);

        stack1.copyFrom(stack2);
        assertSame(BigDecimal.TEN, stack1.pop());
        assertSame(BigDecimal.ZERO, stack1.pop());
        assertTrue("all numbers copied are popped out", stack1.isEmpty());
    }

    @Test
    public void toStringShouldGenerateSpaceSeparatedNumbers() {
        NumberStack stack = new NumberStack();
        stack.push(BigDecimal.ONE);
        stack.push(BigDecimal.TEN);
        stack.push(BigDecimal.ZERO);

        assertEquals("1 10 0", stack.toString());
    }

    @Test
    public void shouldKeep10DecimalPlacesWhenDisplayed() {
        NumberStack stack = new NumberStack();
        stack.push(BigDecimal.valueOf(Math.sqrt(2)));
        assertEquals("1.4142135623", stack.toString());
    }

    @Test
    public void shouldNotDisplayUnnecessaryZeros() {
        NumberStack stack = new NumberStack();
        stack.push(BigDecimal.valueOf(42).divide(BigDecimal.valueOf(4)));
        assertEquals("10.5", stack.toString());
    }

    @Test
    public void shouldOutputEmptyStringIfStackIsEmpty() {
        NumberStack stack = new NumberStack();
        assertEquals("", stack.toString());
    }
}
