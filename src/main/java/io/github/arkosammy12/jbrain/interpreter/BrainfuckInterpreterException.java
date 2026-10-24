package io.github.arkosammy12.jbrain.interpreter;

import io.github.arkosammy12.jbrain.lexer.SourcePosition;

import java.util.Optional;

public class BrainfuckInterpreterException extends Exception {

    private final String message;
    private final SourcePosition sourcePosition;

    public BrainfuckInterpreterException(String message, SourcePosition sourcePosition) {
        this.message = message;
        this.sourcePosition = sourcePosition;
    }

    public BrainfuckInterpreterException(String message) {
        this(message, null);
    }

    public String getMessage() {
        return this.message;
    }

    public Optional<SourcePosition> getSourcePosition() {
        return Optional.ofNullable(this.sourcePosition);
    }

}
