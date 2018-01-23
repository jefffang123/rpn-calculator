package com.airwallex.rpn.operators;

import com.airwallex.rpn.NumberStack;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class PushNumberTest extends NumberStackCommandTest<PushNumber> {

    public PushNumberTest() {
        super(PushNumber::new);
    }

    @Test
    public void testExecute() {
        command.operatorValue = "4.3";

        command.execute();

        verify(numbers).push(BigDecimal.valueOf(4.3));
        verify(undoHistory).push(command);
    }

    @Test
    public void testUndoAfterExecute() {
        numbers = new NumberStack();
        command.setNumberStack(numbers);

        command.setOperatorValue("10");
        command.execute();
        assertEquals("10", numbers.toString());

        command.undo();
        assertEquals("", numbers.toString());
    }
}
