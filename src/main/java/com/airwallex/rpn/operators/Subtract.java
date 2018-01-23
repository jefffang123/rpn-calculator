package com.airwallex.rpn.operators;

import java.math.BigDecimal;

class Subtract extends BinaryMathOperatorCommand {

    @Override
    protected BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return left.subtract(right);
    }
}
