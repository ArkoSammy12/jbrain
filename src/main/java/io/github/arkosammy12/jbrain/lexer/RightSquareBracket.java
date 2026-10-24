package io.github.arkosammy12.jbrain.lexer;

public final class RightSquareBracket extends Token {

    public RightSquareBracket(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof RightSquareBracket;
    }

}
