package com.airwallex.rpn.operators;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MultiplyTest extends NumberStackCommandTest<Multiply> {

    public MultiplyTest() {
        super(Multiply::new);
    }

    @Test
    public void testCalculate() {
        BigDecimal left = BigDecimal.valueOf(2);
        BigDecimal right = BigDecimal.valueOf(3);

        BigDecimal result = command.calculate(left, right);

        assertEquals(BigDecimal.valueOf(6), result);
    }

    @Test
    public void testExecute() {
        when(numbers.size()).thenReturn(2);
        when(numbers.pop()).thenReturn(BigDecimal.valueOf(4), BigDecimal.TEN);
        command.execute();
        verify(numbers).push(BigDecimal.valueOf(40));
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("*");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator * (position: 15): insufficient parameters");

        when(numbers.size()).thenReturn(1);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(BigDecimal.valueOf(0.5));
        numbers.push(BigDecimal.TEN);

        command.execute();
        assertEquals("5", numbers.toString());

        command.undo();
        assertEquals("0.5 10", numbers.toString());
    }
}
