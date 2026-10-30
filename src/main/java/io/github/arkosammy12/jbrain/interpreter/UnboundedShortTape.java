package io.github.arkosammy12.jbrain.interpreter;

public final class UnboundedShortTape extends UnboundedTape<Short> {

    public UnboundedShortTape() {
        super(i -> (short) i);
    }

}
