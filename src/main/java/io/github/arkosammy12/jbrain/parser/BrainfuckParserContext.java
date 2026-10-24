package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.Token;

import java.util.*;

class BrainfuckParserContext {

    private final Queue<Token> tokens;
    private final Collection<BrainfuckNode> nodes = new ArrayList<>();

    BrainfuckParserContext(Queue<Token> tokens) {
        this.tokens = new ArrayDeque<>(tokens);
    }

    Optional<Token> pollToken() {
        return Optional.ofNullable(this.tokens.poll());
    }

    Optional<Token> peekToken() {
        return Optional.ofNullable(this.tokens.peek());
    }

    void addNode(BrainfuckNode brainfuckNode) {
        this.nodes.add(brainfuckNode);
    }

    BrainfuckParseResult.Ok toProgram() {
        return new BrainfuckParseResult.Ok(new BrainfuckProgram(this.nodes));
    }

}
