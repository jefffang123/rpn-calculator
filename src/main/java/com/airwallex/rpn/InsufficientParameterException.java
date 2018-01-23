package com.airwallex.rpn;

public class InsufficientParameterException extends RuntimeException {

    private String operatorValue;
    private int operatorPosition;

    public String getOperatorValue() {
        return operatorValue;
    }

    public int getOperatorPosition() {
        return operatorPosition;
    }

    public InsufficientParameterException(String operatorValue, int operatorPosition) {
        super(String.format("operator %s (position: %d): insufficient parameters", operatorValue, operatorPosition));

        this.operatorValue = operatorValue;
        this.operatorPosition = operatorPosition;
    }
}
