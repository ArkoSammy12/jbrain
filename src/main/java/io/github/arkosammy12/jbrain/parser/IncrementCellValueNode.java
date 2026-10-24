package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.PlusToken;

public final class IncrementCellValueNode extends BrainfuckInstructionNode<PlusToken> {

    private final int increment;

    public IncrementCellValueNode(PlusToken token, int increment) {
        if (increment <= 0) {
            throw new IllegalArgumentException("The cell increment value cannot be zero or negative!");
        }
        super(token);
        this.increment = increment;
    }

    public int getIncrement() {
        return this.increment;
    }

}
