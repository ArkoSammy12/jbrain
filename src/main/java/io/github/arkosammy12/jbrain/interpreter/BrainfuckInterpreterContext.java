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
    private final Tape tape;
    private int cellPointer;

    BrainfuckInterpreterContext(Tape tape, InputStream inputStream, OutputStream outputStream) {
        this.tape = tape;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    void incrementCellValue(int amount) throws BrainfuckInterpreterException {
        this.tape.set(this.cellPointer, this.tape.get(this.cellPointer) + amount);
    }

    void decrementCellValue(int amount) throws BrainfuckInterpreterException {
        this.tape.set(this.cellPointer, this.tape.get(this.cellPointer) - amount);
    }

    void incrementCellPointer(int amount) {
        this.cellPointer += amount;
    }

    void decrementCellPointer(int amount) {
        this.cellPointer -= amount;
    }

    void outputCellValue() throws IOException, BrainfuckInterpreterException {
        int value = this.getCellValue() & 0xFF;
        this.outputStream.write((char) value);
    }

    void inputCellValue() throws IOException, BrainfuckInterpreterException {
        while (this.characterQueue.isEmpty()) {
            this.characterQueue.offer((char) this.inputStream.read());
        }
        this.tape.set(this.cellPointer, (byte) (char) this.characterQueue.poll());
    }

    int getCellValue() throws BrainfuckInterpreterException {
        return this.tape.get(this.cellPointer);
    }

}
