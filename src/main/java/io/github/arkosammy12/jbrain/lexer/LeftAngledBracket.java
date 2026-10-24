package io.github.arkosammy12.jbrain.lexer;

public final class LeftAngledBracket extends Token {

    public LeftAngledBracket(SourcePosition sourcePosition) {
        super(sourcePosition);
    }

    @Override
    public boolean isSameTypeAs(Token other) {
        return other instanceof LeftAngledBracket;
    }

}
