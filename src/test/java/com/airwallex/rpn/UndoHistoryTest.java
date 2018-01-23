package com.airwallex.rpn;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

public class UndoHistoryTest {

    @Test
    public void popAfterPush() {
        Undoable command = mock(Undoable.class);

        UndoHistory undoHistory = new UndoHistory();
        undoHistory.push(command);

        assertSame(command, undoHistory.pop());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotAllowNullElement() {
        UndoHistory undoHistory = new UndoHistory();
        undoHistory.push(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void popShouldFailIfStackIsEmpty() {
        UndoHistory undoHistory = new UndoHistory();
        undoHistory.pop();
    }
}
