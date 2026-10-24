package io.github.arkosammy12.jbrain.parser;

import java.util.Collection;
import java.util.List;

public record BrainfuckProgram(Collection<BrainfuckNode> nodes) {

    public BrainfuckProgram(Collection<BrainfuckNode> nodes) {
        this.nodes = List.copyOf(nodes);
    }

}
