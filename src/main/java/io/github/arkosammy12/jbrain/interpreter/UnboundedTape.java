package io.github.arkosammy12.jbrain.interpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntFunction;

public abstract sealed class UnboundedTape<T extends Number> implements Tape permits UnboundedByteTape, UnboundedIntTape, UnboundedShortTape {

    private final Map<Integer, T> map = new HashMap<>();
    private final IntFunction<T> converter;

    protected UnboundedTape(IntFunction<T> converter) {
        this.converter = converter;
    }

    @Override
    public final void set(int index, int value) {
        this.map.put(index, this.converter.apply(value));
    }

    @Override
    public final int get(int index) {
        return this.map.computeIfAbsent(index, _ -> this.converter.apply(0)).intValue();
    }

}
