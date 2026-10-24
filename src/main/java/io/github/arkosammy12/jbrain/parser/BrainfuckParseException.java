package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.SourcePosition;

class BrainfuckParseException extends Exception {

    private final String message;
    private final SourcePosition sourcePosition;

    BrainfuckParseException(String message, SourcePosition sourcePosition) {
        this.message = message;
        this.sourcePosition = sourcePosition;
    }

    BrainfuckParseResult.Error toError() {
        return new BrainfuckParseResult.Error(this.message, this.sourcePosition);
    }

}
