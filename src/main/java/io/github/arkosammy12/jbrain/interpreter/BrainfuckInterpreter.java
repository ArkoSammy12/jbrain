package io.github.arkosammy12.jbrain.interpreter;

import io.github.arkosammy12.jbrain.parser.*;

import java.io.IOException;
import java.util.Collection;

public class BrainfuckInterpreter {

    private final CellSize cellSize;
    private final boolean dynamicMemory;
    private final MemoryOverflowBehavior memoryOverflowBehavior;
    private final Integer memorySize;

    public BrainfuckInterpreter(CellSize cellSize, boolean dynamicMemory, MemoryOverflowBehavior memoryOverflowBehavior, Integer memorySize) {
        if (dynamicMemory) {
            if (memoryOverflowBehavior != null || memorySize != null) {
                throw new IllegalArgumentException("Cannot specific memory overflow behavior or memory size if using dynamic memory!");
            }
        } else {
            if (memoryOverflowBehavior == null) {
                throw new IllegalArgumentException("Must specify memory overflow behavior if not using dynamic memory size!");
            }
            if (memorySize == null) {
                throw new IllegalArgumentException("Must specify memory size if not using dynamic memory size!");
            }
        }
        this.cellSize = cellSize;
        this.dynamicMemory = dynamicMemory;
        this.memoryOverflowBehavior = memoryOverflowBehavior;
        this.memorySize = memorySize;
    }

    public void interpret(BrainfuckProgram brainfuckProgram) throws IOException, BrainfuckInterpreterException {
        Tape tape;
        if (this.dynamicMemory) {
            tape = switch (this.cellSize) {
                case BITS_8 -> new UnboundedByteTape();
                case BITS_16 -> new UnboundedShortTape();
                case BITS_32 -> new UnboundedIntTape();
            };
        } else {
            tape = switch (this.cellSize) {
                case BITS_8 -> new BoundedByteTape(this.memoryOverflowBehavior, this.memorySize);
                case BITS_16 -> new BoundedShortTape(this.memoryOverflowBehavior, this.memorySize);
                case BITS_32 -> new BoundedIntTape(this.memoryOverflowBehavior, this.memorySize);
            };
        }

        BrainfuckInterpreterContext context = new BrainfuckInterpreterContext(tape, System.in, System.out);
        this.interpretNodes(context, brainfuckProgram.nodes());
    }

    private void interpretNodes(BrainfuckInterpreterContext context, Collection<BrainfuckNode> nodes) throws IOException, BrainfuckInterpreterException {
        for (BrainfuckNode node : nodes) {
            switch (node) {
                case BrainfuckInstructionNode<?> instructionNode -> this.interpretInstructionNode(context, instructionNode);
                case BrainfuckLoopNode loopNode -> this.interpretLoopNode(context, loopNode);
            }
        }
    }

    private void interpretLoopNode(BrainfuckInterpreterContext context, BrainfuckLoopNode loopNode) throws IOException, BrainfuckInterpreterException {
        while (context.getCellValue() != 0) {
            this.interpretNodes(context, loopNode.nodes());
        }
    }

    private void interpretInstructionNode(BrainfuckInterpreterContext context, BrainfuckInstructionNode<?> instructionNode) throws IOException, BrainfuckInterpreterException {
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
