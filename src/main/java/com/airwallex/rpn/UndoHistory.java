package com.airwallex.rpn;

import java.util.ArrayDeque;
import java.util.Deque;

public class UndoHistory {

    private final Deque<Undoable> stack = new ArrayDeque<>();

    public void push(Undoable action) {
        stack.addFirst(action);
    }

    public Undoable pop() {
        return stack.removeFirst();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
