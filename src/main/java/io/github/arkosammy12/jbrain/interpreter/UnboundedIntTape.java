package io.github.arkosammy12.jbrain.interpreter;

public final class UnboundedIntTape extends UnboundedTape<Integer> {

    public UnboundedIntTape() {
        super(i -> i);
    }

}
