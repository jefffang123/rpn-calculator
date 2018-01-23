package com.airwallex.rpn.operators;

import com.airwallex.rpn.NumberStack;
import com.airwallex.rpn.Undoable;

class Clear extends NumberStackCommand implements Undoable {

    private NumberStack state;

    @Override
    public void execute() {
        state = new NumberStack();
        state.copyFrom(numberStack);
        numberStack.clear();

        undoHistory.push(this);
    }

    @Override
    public void undo() {
        numberStack.copyFrom(state);
        state = null;
    }
}
