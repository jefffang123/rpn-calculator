package com.airwallex.rpn.operators;

import com.airwallex.rpn.Command;
import com.airwallex.rpn.NumberStack;
import com.airwallex.rpn.UndoHistory;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public enum Operator {

    PUSH_NUMBER("", PushNumber::new),
    ADD("+", Add::new),
    SUBTRACT("-", Subtract::new),
    MULTIPLY("*", Multiply::new),
    DIVIDE("/", Divide::new),
    SQRT("sqrt", Sqrt::new),
    UNDO("undo", Undo::new),
    CLEAR("clear", Clear::new);

    private String value;
    private Supplier<? extends NumberStackCommand> supplier;

    Operator(String value, Supplier<? extends NumberStackCommand> supplier) {
        this.value = value;
        this.supplier = supplier;
    }

    public Command createCommand(NumberStack numberStack, UndoHistory undoHistory, String operatorValue, int operatorPosition) {
        NumberStackCommand command = supplier.get();
        command.setNumberStack(numberStack);
        command.setUndoHistory(undoHistory);
        command.setOperatorValue(operatorValue);
        command.setOperatorPosition(operatorPosition);

        return command;
    }

    public static Optional<Operator> findByValue(String value) {
        return Arrays.stream(Operator.values()).filter(op -> op.value.equalsIgnoreCase(value)).findFirst();
    }
}
