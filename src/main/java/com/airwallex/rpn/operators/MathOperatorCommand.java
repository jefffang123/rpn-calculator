package com.airwallex.rpn.operators;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.Undoable;

import java.math.BigDecimal;

abstract class MathOperatorCommand extends NumberStackCommand implements Undoable {

    private BigDecimal left;
    private BigDecimal right;

    @Override
    public final void execute() {
        takeOperands();

        BigDecimal result = calculate(left, right);
        numberStack.push(result);

        undoHistory.push(this);
    }

    @Override
    public final void undo() {
        numberStack.pop();

        numberStack.push(left);
        if (isBinary()) {
            numberStack.push(right);
        }
    }

    private void takeOperands() {
        if (isBinary() && numberStack.size() < 2 ||
                !isBinary() && numberStack.size() < 1) {
            throw new InsufficientParameterException(operatorValue, operatorPosition);
        }

        if (isBinary()) {
            right = numberStack.pop();
        }
        left = numberStack.pop();
    }

    protected abstract boolean isBinary();

    protected abstract BigDecimal calculate(BigDecimal left, BigDecimal right);
}
