package com.airwallex.rpn.operators;

abstract class BinaryOperator extends ArithmeticOperator {

    @Override
    protected final boolean isBinary() {
        return true;
    }
}
