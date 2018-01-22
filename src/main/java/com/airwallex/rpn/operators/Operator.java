package com.airwallex.rpn.operators;

import com.airwallex.rpn.Command;
import com.airwallex.rpn.NumberStack;

import java.util.Arrays;
import java.util.Optional;

public enum Operator {

    ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"), SQRT("sqrt"), UNDO("undo"), CLEAR("clear");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    public Command createCommand(NumberStack numberStack) {
        // TODO:

        return null;
    }

    public static Optional<Operator> findByValue(String value) {
        return Arrays.stream(Operator.values()).filter(op -> op.value.equalsIgnoreCase(value)).findFirst();
    }
}
