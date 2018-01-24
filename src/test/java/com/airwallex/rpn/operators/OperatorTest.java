package com.airwallex.rpn.operators;

import com.airwallex.rpn.Command;
import com.airwallex.rpn.NumberStack;
import com.airwallex.rpn.UndoHistory;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class OperatorTest {

    @Test
    public void testAdd() {
        Operator op = Operator.findByValue("+").get();
        assertSame(Operator.ADD, op);

        verifyCreateCommand(op, "+", Add.class);
    }

    @Test
    public void testSubtract() {
        Operator op = Operator.findByValue("-").get();
        assertSame(Operator.SUBTRACT, op);

        verifyCreateCommand(op, "-", Subtract.class);
    }

    @Test
    public void testMultiply() {
        Operator op = Operator.findByValue("*").get();
        assertSame(Operator.MULTIPLY, op);

        verifyCreateCommand(op, "*", Multiply.class);
    }

    @Test
    public void testDivide() {
        Operator op = Operator.findByValue("/").get();
        assertSame(Operator.DIVIDE, op);

        verifyCreateCommand(op, "/", Divide.class);
    }

    @Test
    public void testSqrt() {
        Operator op = Operator.findByValue("sqrt").get();
        assertSame(Operator.SQRT, op);

        verifyCreateCommand(op, "sqrt", Sqrt.class);
    }

    @Test
    public void testUndo() {
        Operator op = Operator.findByValue("undo").get();
        assertSame(Operator.UNDO, op);

        verifyCreateCommand(op, "undo", Undo.class);
    }

    @Test
    public void testClear() {
        Operator op = Operator.findByValue("clear").get();
        assertSame(Operator.CLEAR, op);

        verifyCreateCommand(op, "clear", Clear.class);
    }

    @Test
    public void testPushNumber() {
        Operator op = Operator.findByValue("13.4").get();
        assertSame(Operator.PUSH_NUMBER, op);

        verifyCreateCommand(op, "13.4", PushNumber.class);
    }

    @Test
    public void testUnknownOperator() {
        Optional op = Operator.findByValue("cos");
        assertFalse(op.isPresent());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotAllowNullParameter() {
        Operator.findByValue(null);
    }

    private void verifyCreateCommand(Operator op, String operatorValue, Class commandClass) {
        NumberStack numberStack = mock(NumberStack.class);
        UndoHistory undoHistory = mock(UndoHistory.class);

        Command command = op.createCommand(numberStack, undoHistory, operatorValue, 10);

        assertTrue(commandClass.isInstance(command));
    }
}
