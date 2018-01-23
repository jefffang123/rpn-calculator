package com.airwallex.rpn.operators;

import java.math.BigDecimal;

class Add extends BinaryMathOperatorCommand {

    @Override
    protected BigDecimal calculate(BigDecimal left, BigDecimal right) {
        return left.add(right);
    }
}
