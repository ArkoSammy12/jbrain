package io.github.arkosammy12.jbrain.lexer;

public final class RightAngledBracket extends Token {

    public RightAngledBracket(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof RightAngledBracket;
    }

}
