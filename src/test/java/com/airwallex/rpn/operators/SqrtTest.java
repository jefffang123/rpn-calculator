package com.airwallex.rpn.operators;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SqrtTest extends NumberStackCommandTest<Sqrt> {

    public SqrtTest() {
        super(Sqrt::new);
    }

    @Test
    public void testCalculate() {
        BigDecimal result = command.calculate(BigDecimal.valueOf(16));

        assertEquals(BigDecimal.valueOf(4), result);
    }

    @Test
    public void testExecute() {
        when(numbers.size()).thenReturn(1);
        when(numbers.pop()).thenReturn(BigDecimal.valueOf(4));
        command.execute();
        verify(numbers).push(BigDecimal.valueOf(2));
        verify(undoHistory).push(command);
    }

    @Test
    public void shouldThrowExceptionIfNotEnoughNumber() {
        command.setOperatorValue("sqrt");
        command.setOperatorPosition(15);

        expectedEx.expect(InsufficientParameterException.class);
        expectedEx.expectMessage("operator sqrt (position: 15): insufficient parameters");

        when(numbers.size()).thenReturn(0);
        command.execute();
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(BigDecimal.valueOf(4));

        command.execute();
        assertEquals("2", numbers.toString());

        command.undo();
        assertEquals("4", numbers.toString());
    }
}
