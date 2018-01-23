package com.airwallex.rpn.operators;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SubtractTest extends NumberStackCommandTest<Subtract> {

    public SubtractTest() {
        super(Subtract::new);
    }

    @Test
    public void testCalculate() {
        BigDecimal left = BigDecimal.valueOf(4);
        BigDecimal right = BigDecimal.valueOf(3.1);

        BigDecimal result = command.calculate(left, right);

        assertEquals(BigDecimal.valueOf(0.9), result);
    }

    @Test
    public void testExecute() {
        when(numbers.pop()).thenReturn(BigDecimal.valueOf(4), BigDecimal.ONE);
        command.execute();
        verify(numbers).push(BigDecimal.valueOf(-3));
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("-");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator - (position: 15): insufficient parameters");

        when(numbers.pop()).thenReturn(BigDecimal.ONE).thenThrow(NoSuchElementException.class);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(BigDecimal.ONE);
        numbers.push(BigDecimal.TEN);

        command.execute();
        assertEquals("-9", numbers.toString());

        command.undo();
        assertEquals("1 10", numbers.toString());
    }
}
