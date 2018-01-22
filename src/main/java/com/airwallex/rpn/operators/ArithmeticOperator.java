package com.airwallex.rpn.operators;

import com.airwallex.rpn.Undoable;

import java.math.BigDecimal;

abstract class ArithmeticOperator extends OperatorCommand implements Undoable {

    private BigDecimal left;
    private BigDecimal right;

    @Override
    public final void execute() {
        takeOperands();

        BigDecimal result = calculate(left, right);
        numberStack.push(result);
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
