package com.airwallex.rpn;

public class InsufficientParameterException extends RuntimeException {

    public InsufficientParameterException(String operatorValue, int operatorPosition) {
        super(String.format("operator %s (position: %d): insufficient parameters", operatorValue, operatorPosition));
    }
}
