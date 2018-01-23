package com.airwallex.rpn;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class NumberStack {

    private final Deque<BigDecimal> stack = new ArrayDeque<>();

    public void push(BigDecimal number) {
        stack.addFirst(number);
    }

    public BigDecimal pop() {
        return stack.removeFirst();
    }

    public void clear() {
        stack.clear();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void copyFrom(NumberStack other) {
        stack.clear();
        stack.addAll(other.stack);
    }

    @Override
    public String toString() {
        Iterable<BigDecimal> iterable = () -> stack.descendingIterator();

        return StreamSupport.stream(iterable.spliterator(), false)
                .map(NumberStack::formatNumber).collect(Collectors.joining(" "));
    }

    private static String formatNumber(BigDecimal number) {
        BigDecimal bd = number.setScale(10, BigDecimal.ROUND_DOWN);

        NumberFormat f = NumberFormat.getInstance();
        f.setMaximumFractionDigits(10);
        f.setMinimumFractionDigits(0);
        f.setGroupingUsed(false);

        return f.format(bd);
    }
}
