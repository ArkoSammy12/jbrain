package io.github.arkosammy12.jbrain.lexer;

public final class MinusToken extends Token {

    public MinusToken(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof MinusToken;
    }

}
