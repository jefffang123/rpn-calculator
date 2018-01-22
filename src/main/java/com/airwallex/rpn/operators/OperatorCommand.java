package com.airwallex.rpn.operators;

import com.airwallex.rpn.Command;
import com.airwallex.rpn.NumberStack;

abstract class OperatorCommand implements Command {

    protected NumberStack numberStack;

    public void setNumberStack(NumberStack numberStack) {
        this.numberStack = numberStack;
    }
}
