package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.SourcePosition;

public sealed interface BrainfuckParseResult {

    record Ok(BrainfuckProgram brainfuckProgram) implements BrainfuckParseResult {}

    record Error(String message, SourcePosition sourcePosition) implements BrainfuckParseResult {}

}
