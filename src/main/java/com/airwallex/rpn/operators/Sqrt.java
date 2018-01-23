package com.airwallex.rpn.operators;

import java.math.BigDecimal;

class Sqrt extends UnaryMathOperatorCommand {

    @Override
    protected BigDecimal calculate(BigDecimal operand) {
        // TODO: Use BigDecimal::sqrt in Java 9 or pre Java 9 use 3rd-party library to keep precision.
        return new BigDecimal(Math.sqrt(operand.doubleValue()));
    }
}
