package io.github.arkosammy12.jbrain.parser;

import java.util.Collection;
import java.util.List;

public record BrainfuckLoopNode(Collection<BrainfuckNode> nodes) implements BrainfuckNode {

    public BrainfuckLoopNode(Collection<BrainfuckNode> nodes) {
        this.nodes = List.copyOf(nodes);
    }

}
