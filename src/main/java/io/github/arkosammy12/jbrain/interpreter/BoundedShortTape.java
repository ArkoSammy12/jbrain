package io.github.arkosammy12.jbrain.interpreter;

public final class BoundedShortTape extends BoundedTape {

    private final short[] arr;

    public BoundedShortTape(MemoryOverflowBehavior memoryOverflowBehavior, int memorySize) {
        super(memoryOverflowBehavior, memorySize);
        this.arr = new short[this.memorySize];
    }

    @Override
    protected void setArray(int index, int value) {
        this.arr[index] = (short) value;
    }

    @Override
    protected int getArray(int index) {
        return this.arr[index];
    }
}
