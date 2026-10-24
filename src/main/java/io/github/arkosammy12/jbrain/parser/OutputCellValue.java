package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.PeriodToken;

public final class OutputCellValue extends BrainfuckInstructionNode<PeriodToken> {

    public OutputCellValue(PeriodToken token) {
        super(token);
    }

}
