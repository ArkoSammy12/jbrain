package io.github.arkosammy12.jbrain.interpreter;

public final class UnboundedByteTape extends UnboundedTape<Byte> {

    public UnboundedByteTape() {
        super(i -> (byte) i);
    }

}
