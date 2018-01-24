package com.airwallex.rpn.operators;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DivideTest extends NumberStackCommandTest<Divide> {

    public DivideTest() {
        super(Divide::new);
    }

    @Test
    public void testCalculate() {
        BigDecimal left = BigDecimal.valueOf(4);
        BigDecimal right = BigDecimal.valueOf(2);

        BigDecimal result = command.calculate(left, right);

        assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    public void testExecute() {
        when(numbers.size()).thenReturn(2);
        when(numbers.pop()).thenReturn(BigDecimal.valueOf(4), BigDecimal.ONE);
        command.execute();
        verify(numbers).push(BigDecimal.valueOf(0.25));
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("/");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator / (position: 15): insufficient parameters");

        when(numbers.size()).thenReturn(1);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(BigDecimal.ONE);
        numbers.push(BigDecimal.TEN);

        command.execute();
        assertEquals("0.1", numbers.toString());

        command.undo();
        assertEquals("1 10", numbers.toString());
    }
}
