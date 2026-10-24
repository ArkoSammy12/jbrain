package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.HashToken;

public final class OutputDebugNode extends BrainfuckInstructionNode<HashToken> {

    public OutputDebugNode(HashToken token) {
        super(token);
    }

}
