package io.github.arkosammy12.jbrain.interpreter;

import io.github.arkosammy12.jbrain.parser.*;

import java.io.IOException;
import java.util.Collection;

public class BrainfuckInterpreter {

    private final int tapeLength;

    public BrainfuckInterpreter(int tapeLength) {
        if (tapeLength <= 0) {
            throw new IllegalArgumentException("The interpreter tape length cannot be zero or negative!");
        }
        this.tapeLength = tapeLength;
    }

    public void interpret(BrainfuckProgram brainfuckProgram) throws IOException {
        BrainfuckInterpreterContext context = new BrainfuckInterpreterContext(this.tapeLength, System.in, System.out);
        this.interpretNodes(context, brainfuckProgram.nodes());
    }

    private void interpretNodes(BrainfuckInterpreterContext context, Collection<BrainfuckNode> nodes) throws IOException {
        for (BrainfuckNode node : nodes) {
            switch (node) {
                case BrainfuckInstructionNode<?> instructionNode -> this.interpretInstructionNode(context, instructionNode);
                case BrainfuckLoopNode loopNode -> this.interpretLoopNode(context, loopNode);
            }
        }
    }

    private void interpretLoopNode(BrainfuckInterpreterContext context, BrainfuckLoopNode loopNode) throws IOException {
        while (context.getCellValue() != 0) {
            this.interpretNodes(context, loopNode.nodes());
        }
    }

    private void interpretInstructionNode(BrainfuckInterpreterContext context, BrainfuckInstructionNode<?> instructionNode) throws IOException {
        switch (instructionNode) {
            case IncrementCellValueNode incrementCellValueNode -> context.incrementCellValue(incrementCellValueNode.getIncrement());
            case DecrementCellValueNode decrementCellValueNode -> context.decrementCellValue(decrementCellValueNode.getDecrement());
            case IncrementCellPointerNode incrementCellPointerNode -> context.incrementCellPointer(incrementCellPointerNode.getMove());
            case DecrementCellPointerNode decrementCellPointerNode -> context.decrementCellPointer(decrementCellPointerNode.getMove());
            case OutputCellValue _ -> context.outputCellValue();
            case InputCellValue _ -> context.inputCellValue();
            case OutputDebugNode _ -> {
                // TODO: Implement
            }
        }
    }

}
