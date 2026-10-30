package io.github.arkosammy12.jbrain.interpreter;

public final class BoundedIntTape extends BoundedTape {

    private final int[] arr;

    public BoundedIntTape(MemoryOverflowBehavior memoryOverflowBehavior, int memorySize) {
        super(memoryOverflowBehavior, memorySize);
        this.arr = new int[this.memorySize];
    }

    @Override
    protected void setArray(int index, int value) {
        this.arr[index] = value;
    }

    @Override
    protected int getArray(int index) {
        return this.arr[index];
    }

}
