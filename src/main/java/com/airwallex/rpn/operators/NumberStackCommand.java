package com.airwallex.rpn.operators;

import com.airwallex.rpn.Command;
import com.airwallex.rpn.NumberStack;
import com.airwallex.rpn.UndoHistory;

abstract class NumberStackCommand implements Command {

    protected NumberStack numberStack;
    protected UndoHistory undoHistory;
    protected String operatorValue;
    protected int operatorPosition;

    public void setNumberStack(NumberStack numberStack) {
        this.numberStack = numberStack;
    }

    public void setUndoHistory(UndoHistory undoHistory) {
        this.undoHistory = undoHistory;
    }

    public void setOperatorValue(String operatorValue) {
        this.operatorValue = operatorValue;
    }

    public void setOperatorPosition(int operatorPosition) {
        this.operatorPosition = operatorPosition;
    }
}
