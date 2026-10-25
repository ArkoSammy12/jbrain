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
        int row = 0;
        for (String line : lines) {
            for (int column = 0; column < line.length(); column++) {
                switch (line.charAt(column)) {
                    case '+' -> tokens.add(new PlusToken(new SourcePosition(row, column)));
                    case '-' -> tokens.add(new MinusToken(new SourcePosition(row, column)));
                    case '<' -> tokens.add(new LeftAngledBracket(new SourcePosition(row, column)));
                    case '>' -> tokens.add(new RightAngledBracket(new SourcePosition(row, column)));
                    case '[' -> tokens.add(new LeftSquareBracketToken(new SourcePosition(row, column)));
                    case ']' -> tokens.add(new RightSquareBracket(new SourcePosition(row, column)));
                    case '.' -> tokens.add(new PeriodToken(new SourcePosition(row, column)));
                    case ',' -> tokens.add(new CommaToken(new SourcePosition(row, column)));
                    case '#' -> {
                        if (this.hashAsDebug) {
                            tokens.add(new HashToken(new SourcePosition(row, column)));
                        }
                    }
                }
            }
            row++;
        }
        return tokens;
    }

}
