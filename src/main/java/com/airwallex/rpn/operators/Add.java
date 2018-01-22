package com.airwallex.rpn.operators;

import java.math.BigDecimal;

class Add extends BinaryOperator {

    @Override
    protected BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return left.add(right);
    }
}
