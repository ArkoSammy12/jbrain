package io.github.arkosammy12.jbrain.interpreter;

public abstract sealed class BoundedTape implements Tape permits BoundedByteTape, BoundedIntTape, BoundedShortTape {

    private final MemoryOverflowBehavior memoryOverflowBehavior;
    protected final int memorySize;

    public BoundedTape(MemoryOverflowBehavior memoryOverflowBehavior, int memorySize) {
        this.memoryOverflowBehavior = memoryOverflowBehavior;
        this.memorySize = memorySize;
    }

    @Override
    public final void set(int index, int value) throws BrainfuckInterpreterException {
        this.setArray(this.checkIndex(index), value);
    }

    @Override
    public final int get(int index) throws BrainfuckInterpreterException {
        return this.getArray(this.checkIndex(index));
    }

    abstract protected void setArray(int index, int value);

    abstract protected int getArray(int index);

    private int checkIndex(int index) throws BrainfuckInterpreterException {
        return switch (this.memoryOverflowBehavior) {
            case UNDEFINED -> index;
            case ABORT -> {
                if (index < 0 || index >= this.memorySize) {
                    throw new BrainfuckInterpreterException("Cell pointer value '%d' is out of bounds for memory size '%d'!".formatted(index, this.memorySize));
                } else {
                    yield index;
                }
            }
            case WRAP -> ((index % this.memorySize) + this.memorySize) % this.memorySize;
        };
    }

}
