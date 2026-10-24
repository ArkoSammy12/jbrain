package io.github.arkosammy12.jbrain;

import io.github.arkosammy12.jbrain.interpreter.BrainfuckInterpreter;
import io.github.arkosammy12.jbrain.lexer.BrainfuckLexer;
import io.github.arkosammy12.jbrain.lexer.Token;
import io.github.arkosammy12.jbrain.parser.BrainfuckParseResult;
import io.github.arkosammy12.jbrain.parser.BrainfuckParser;
import io.github.arkosammy12.jbrain.parser.BrainfuckProgram;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Queue;

public class Main {

    static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Expected file path argument!");
            return;
        }

        Path filePath = Paths.get(args[0]);
        if (!Files.exists(filePath)) {
            System.err.println("The provided path '%s' does not exist!".formatted(filePath.toString()));
        }

        Collection<String> lines;
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.err.println("Error reading from path '%s': %s".formatted(filePath, e.getCause()));
            return;
        }

        BrainfuckLexer lexer = new BrainfuckLexer();
        Queue<Token> tokens = lexer.lex(lines);
        BrainfuckParser parser = new BrainfuckParser();
        switch (parser.parse(tokens)) {
            case BrainfuckParseResult.Error error -> System.err.println("Failed to compile provided Brainfuck program!\n(%d:%d) %s".formatted(error.sourcePosition().column() + 1, error.sourcePosition().row() + 1, error.message()));
            case BrainfuckParseResult.Ok(BrainfuckProgram brainfuckProgram) -> {
                BrainfuckInterpreter interpreter = new BrainfuckInterpreter(30_000);
                interpreter.interpret(brainfuckProgram);
            }
        }
    }

}
