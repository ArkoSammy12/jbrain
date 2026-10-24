package io.github.arkosammy12.jbrain.lexer;

public final class HashToken extends Token {

    public HashToken(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof HashToken;
    }

}
