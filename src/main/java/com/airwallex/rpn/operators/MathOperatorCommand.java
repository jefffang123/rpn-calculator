package com.airwallex.rpn.operators;

import com.airwallex.rpn.InsufficientParameterException;
import com.airwallex.rpn.Undoable;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

abstract class MathOperatorCommand extends NumberStackCommand implements Undoable {

    private BigDecimal left;
    private BigDecimal right;

    @Override
    public final void execute() {
        try {
            takeOperands();
        } catch (NoSuchElementException e) {
            throw new InsufficientParameterException(operatorValue, operatorPosition);
        }

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
        if (isBinary()) {
            right = numberStack.pop();
        }
        left = numberStack.pop();
    }

    protected abstract boolean isBinary();

    protected abstract BigDecimal calculate(BigDecimal left, BigDecimal right);
}
