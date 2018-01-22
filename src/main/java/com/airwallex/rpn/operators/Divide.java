package com.airwallex.rpn.operators;

import java.math.BigDecimal;

class Divide extends BinaryOperator {

    @Override
    protected BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return left.divide(right);
    }
}
