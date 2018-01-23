package com.airwallex.rpn.operators;

abstract class BinaryMathOperatorCommand extends MathOperatorCommand {

    @Override
    protected final boolean isBinary() {
        return true;
    }
}
