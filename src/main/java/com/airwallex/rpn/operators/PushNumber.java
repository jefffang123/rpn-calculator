package com.airwallex.rpn.operators;

import com.airwallex.rpn.Undoable;

import java.math.BigDecimal;

class PushNumber extends NumberStackCommand implements Undoable {

    @Override
    public void execute() {
        numberStack.push(new BigDecimal(operatorValue));

        undoHistory.push(this);
    }

    @Override
    public void undo() {
        numberStack.pop();
    }
}
