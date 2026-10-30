package io.github.arkosammy12.jbrain.interpreter;

public final class BoundedByteTape extends BoundedTape {

    private final byte[] arr;

    public BoundedByteTape(MemoryOverflowBehavior memoryOverflowBehavior, int memorySize) {
        super(memoryOverflowBehavior, memorySize);
        this.arr = new byte[this.memorySize];
    }

    @Override
    protected void setArray(int index, int value) {
        this.arr[index] = (byte) value;
    }

    @Override
    protected int getArray(int index) {
        return this.arr[index];
    }

}
