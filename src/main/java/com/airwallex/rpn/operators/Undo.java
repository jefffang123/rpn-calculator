package com.airwallex.rpn.operators;

class Undo extends NumberStackCommand {

    @Override
    public void execute() {
        if (!undoHistory.isEmpty()) {
            undoHistory.pop().undo();
        }
    }
}
