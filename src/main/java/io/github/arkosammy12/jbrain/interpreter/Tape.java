package io.github.arkosammy12.jbrain.interpreter;

public sealed interface Tape permits BoundedTape, UnboundedTape {

    void set(int index, int value) throws BrainfuckInterpreterException;

    int get(int index) throws BrainfuckInterpreterException;

}
