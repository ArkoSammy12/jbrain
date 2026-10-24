package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.Token;

public abstract sealed class BrainfuckInstructionNode<T extends Token> implements BrainfuckNode permits
        DecrementCellPointerNode,
        DecrementCellValueNode,
        IncrementCellPointerNode,
        IncrementCellValueNode,
        InputCellValue,
        OutputCellValue,
        OutputDebugNode {

    private final T token;

    public BrainfuckInstructionNode(T token) {
        this.token = token;
    }

    public T getToken() {
        return this.token;
    }

}
