package io.github.arkosammy12.jbrain.lexer;

public abstract sealed class Token permits CommaToken, HashToken, LeftAngledBracket, LeftSquareBracketToken, MinusToken, PeriodToken, PlusToken, RightAngledBracket, RightSquareBracket {

    private final SourcePosition sourcePosition;

    public Token(SourcePosition sourcePosition) {
        this.sourcePosition = sourcePosition;
    }

    public SourcePosition getSourcePosition() {
        return this.sourcePosition;
    }

    public abstract boolean isSameTypeAs(Token other);

}
