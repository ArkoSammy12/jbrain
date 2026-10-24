package io.github.arkosammy12.jbrain.interpreter;

import java.util.ArrayDeque;
import java.util.Queue;

class BrainfuckInterpreterContext {

    private final Queue<Character> characterQueue = new ArrayDeque<>();
    private final byte[] tape;
    private int cellPointer;

    BrainfuckInterpreterContext(int tapeLength) {
        if (tapeLength <= 0) {
            throw new IllegalArgumentException("The interpreter tape length cannot be zero or negative!");
        }
        this.tape = new byte[tapeLength];
    }

    void incrementCellValue(int amount) {
        this.tape[this.cellPointer % this.tape.length] = (byte) (this.tape[this.cellPointer % this.tape.length] + amount);
    }

    void decrementCellValue(int amount) {
        this.tape[this.cellPointer % this.tape.length] = (byte) (this.tape[this.cellPointer % this.tape.length] - amount);
    }

    void incrementCellPointer(int amount) {
        this.cellPointer += amount;
    }

    void decrementCellPointer(int amount) {
        this.cellPointer -= amount;
    }

    void outputCellValue() {
        int value = this.getCellValue() & 0xFF;
        IO.print((char) value);
    }

    void inputCellValue() {
        while (this.characterQueue.isEmpty()) {
            for (char c : IO.readln().toCharArray()) {
                this.characterQueue.offer(c);
            }
        }
        this.tape[this.cellPointer % this.tape.length] = (byte) (char) this.characterQueue.poll();
    }

    byte getCellValue() {
        return this.tape[this.cellPointer % this.tape.length];
    }

}
