package com.airwallex.rpn.operators;

import java.math.BigDecimal;

class Multiply extends BinaryMathOperatorCommand {

    @Override
    protected BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return left.multiply(right);
    }
}
