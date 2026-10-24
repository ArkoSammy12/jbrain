package io.github.arkosammy12.jbrain.lexer;

public final class LeftSquareBracketToken extends Token {

    public LeftSquareBracketToken(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof LeftSquareBracketToken;
    }

}
