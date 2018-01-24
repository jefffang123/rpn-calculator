package com.airwallex.rpn;

import com.airwallex.rpn.operators.Operator;

import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class Calculator {

    private final NumberStack numberStack = new NumberStack();
    private final UndoHistory undoHistory = new UndoHistory();

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String line;
        while (true) {
            line = scanner.nextLine();
            if ("exit".equalsIgnoreCase(line)) {
                break;
            }

            try {
                processLine(line);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            } finally {
                System.out.println("stack: " + numberStack);
            }
        }
    }

    void processLine(String line) {
        Stream.of(line.split("\\s+")).forEach(this::processValue);
    }

    private void processValue(String value) {
        Operator op = Operator.findByValue(value).orElse(null);
        Objects.requireNonNull(op, "invalid number or unknown operator: '" + value + "'");

        op.createCommand(numberStack, undoHistory, value, 1).execute();
    }

    public String getStackMessage() {
        return numberStack.toString();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}
