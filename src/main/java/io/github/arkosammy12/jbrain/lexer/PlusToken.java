package io.github.arkosammy12.jbrain.lexer;

public final class PlusToken extends Token {

    public PlusToken(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof PlusToken;
    }

}
