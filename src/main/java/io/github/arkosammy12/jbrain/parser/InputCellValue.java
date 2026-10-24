package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.CommaToken;

public final class InputCellValue extends BrainfuckInstructionNode<CommaToken> {

    public InputCellValue(CommaToken token) {
        super(token);
    }

}
