package io.github.arkosammy12.jbrain.lexer;

public record SourcePosition(int row, int column) {

    public SourcePosition {
        if (this.column() < 0) {
            throw new IllegalArgumentException("Source position column '%d' cannot be less than zero!".formatted(this.column()));
        }
        if (this.row() < 0) {
            throw new IllegalArgumentException("Source position row '%d' cannot be less than zero!".formatted(this.row()));
        }
    }

}
