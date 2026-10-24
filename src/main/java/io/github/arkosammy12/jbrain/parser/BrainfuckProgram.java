package io.github.arkosammy12.jbrain.parser;

import java.util.Collection;
import java.util.List;

// TODO: Optimization passes with a bottom-up approach (post-order AST traversal) by scanning and replacing common patterns
public record BrainfuckProgram(Collection<BrainfuckNode> nodes) {

    public BrainfuckProgram(Collection<BrainfuckNode> nodes) {
        this.nodes = List.copyOf(nodes);
    }

}
