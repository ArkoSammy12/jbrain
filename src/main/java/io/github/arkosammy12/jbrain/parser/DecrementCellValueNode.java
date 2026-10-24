package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.MinusToken;

public final class DecrementCellValueNode extends BrainfuckInstructionNode<MinusToken> {

    private final int decrement;

    public DecrementCellValueNode(MinusToken token, int decrement) {
        if (decrement <= 0) {
            throw new IllegalArgumentException("The cell decrement value cannot be zero or negative!");
        }
        super(token);
        this.decrement = decrement;
    }

    public int getDecrement() {
        return this.decrement;
    }

}