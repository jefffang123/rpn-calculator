package com.airwallex.rpn.operators;

import java.math.BigDecimal;

abstract class UnaryOperator extends ArithmeticOperator {

    @Override
    protected final boolean isBinary() {
        return false;
    }

    @Override
    protected final BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return calculate(left);
    }

    protected abstract BigDecimal calculate(BigDecimal operand);
}
