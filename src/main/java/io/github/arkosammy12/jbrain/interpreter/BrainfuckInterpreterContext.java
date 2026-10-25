package io.github.arkosammy12.jbrain.interpreter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Queue;

class BrainfuckInterpreterContext {

    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final Queue<Character> characterQueue = new ArrayDeque<>();
    private final byte[] tape;
    private int cellPointer;

    BrainfuckInterpreterContext(int tapeLength, InputStream inputStream, OutputStream outputStream) {
        if (tapeLength <= 0) {
            throw new IllegalArgumentException("The interpreter tape length cannot be zero or negative!");
        }
        this.tape = new byte[tapeLength];
        this.inputStream = inputStream;
        this.outputStream = outputStream;
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

    void outputCellValue() throws IOException {
        int value = this.getCellValue() & 0xFF;
        this.outputStream.write((char) value);
    }

    void inputCellValue() throws IOException {
        while (this.characterQueue.isEmpty()) {
            this.characterQueue.offer((char) this.inputStream.read());
        }
        this.tape[this.cellPointer % this.tape.length] = (byte) (char) this.characterQueue.poll();
    }

    byte getCellValue() {
        return this.tape[this.cellPointer % this.tape.length];
    }

}
