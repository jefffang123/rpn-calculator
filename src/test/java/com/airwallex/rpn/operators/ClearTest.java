package com.airwallex.rpn.operators;

import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ClearTest extends NumberStackCommandTest<Clear> {

    public ClearTest() {
        super(Clear::new);
    }

    @Test
    public void testExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);
        numbers.push(BigDecimal.ONE);
        numbers.push(BigDecimal.TEN);

        command.execute();

        assertTrue("number stack should be empty after cleared", numbers.isEmpty());
        verify(undoHistory).push(command);
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        numbers.push(BigDecimal.ONE);
        numbers.push(BigDecimal.TEN);

        command.execute();
        assertEquals("", numbers.toString());

        command.undo();
        assertEquals("1 10", numbers.toString());
    }

    @Test
    public void shouldBehaveProperlyOnEmptyStack() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        command.execute();
        assertEquals("", numbers.toString());

        command.undo();
        assertEquals("", numbers.toString());
    }
}
