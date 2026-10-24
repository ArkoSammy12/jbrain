package io.github.arkosammy12.jbrain.lexer;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

public final class BrainfuckLexer {

    private final boolean hashAsDebug;

    public BrainfuckLexer(boolean hashAsDebug) {
        this.hashAsDebug = hashAsDebug;
    }

    public BrainfuckLexer() {
        this(false);
    }

    public Queue<Token> lex(Collection<String> lines) {
        Queue<Token> tokens = new ArrayDeque<>();
        int column = 0;
        for (String line : lines) {
            for (int row = 0; row < line.length(); row++) {
                switch (line.charAt(row)) {
                    case '+' -> tokens.add(new PlusToken(new SourcePosition(column, row)));
                    case '-' -> tokens.add(new MinusToken(new SourcePosition(column, row)));
                    case '<' -> tokens.add(new LeftAngledBracket(new SourcePosition(column, row)));
                    case '>' -> tokens.add(new RightAngledBracket(new SourcePosition(column, row)));
                    case '[' -> tokens.add(new LeftSquareBracketToken(new SourcePosition(column, row)));
                    case ']' -> tokens.add(new RightSquareBracket(new SourcePosition(column, row)));
                    case '.' -> tokens.add(new PeriodToken(new SourcePosition(column, row)));
                    case ',' -> tokens.add(new CommaToken(new SourcePosition(column, row)));
                    case '#' -> {
                        if (this.hashAsDebug) {
                            tokens.add(new HashToken(new SourcePosition(column, row)));
                        }
                    }
                }
            }
            column++;
        }
        return tokens;
    }

}
