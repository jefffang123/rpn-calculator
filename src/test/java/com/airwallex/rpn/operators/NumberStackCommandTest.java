package com.airwallex.rpn.operators;

import com.airwallex.rpn.NumberStack;
import com.airwallex.rpn.UndoHistory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.function.Supplier;

import static org.mockito.Mockito.mock;

public abstract class NumberStackCommandTest<T extends NumberStackCommand> {

    private Supplier<T> supplier;

    protected T command;
    protected NumberStack numbers;
    protected UndoHistory undoHistory;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    protected NumberStackCommandTest(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Before
    public void setup() {
        command = supplier.get();

        numbers = mock(NumberStack.class);
        undoHistory = mock(UndoHistory.class);
        command.setNumberStack(numbers);
        command.setUndoHistory(undoHistory);
    }
}
