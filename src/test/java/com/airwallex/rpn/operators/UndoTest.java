package com.airwallex.rpn.operators;

import com.airwallex.rpn.Undoable;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class UndoTest extends NumberStackCommandTest<Undo> {

    public UndoTest() {
        super(Undo::new);
    }

    @Test
    public void testExecute() {
        Undoable lastCommand = mock(Undoable.class);

        when(undoHistory.isEmpty()).thenReturn(false);
        when(undoHistory.pop()).thenReturn(lastCommand);
        command.execute();

        verify(lastCommand).undo();
        verify(undoHistory, never()).push(any());
    }

    @Test
    public void shouldKeepQuietIfUndoHistoryIsEmpty() {
        when(undoHistory.isEmpty()).thenReturn(true);
        command.execute();

        verify(undoHistory, never()).push(any());
    }
}
