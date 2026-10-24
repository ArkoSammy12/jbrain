package io.github.arkosammy12.jbrain.lexer;

public final class CommaToken extends Token {

    public CommaToken(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof CommaToken;
    }

}
