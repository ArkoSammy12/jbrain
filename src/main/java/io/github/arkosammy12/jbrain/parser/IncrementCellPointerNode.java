package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.RightAngledBracket;

public final class IncrementCellPointerNode extends BrainfuckInstructionNode<RightAngledBracket> {

    private final int move;

    public IncrementCellPointerNode(RightAngledBracket token, int move) {
        if (move <= 0) {
            throw new IllegalArgumentException("The cell pointer increment value cannot be zero or negative!");
        }
        super(token);
        this.move = move;
    }

    public int getMove() {
        return this.move;
    }

}
