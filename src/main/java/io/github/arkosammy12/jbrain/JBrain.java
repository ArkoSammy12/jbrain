package io.github.arkosammy12.jbrain;

import io.github.arkosammy12.jbrain.interpreter.BrainfuckInterpreter;
import io.github.arkosammy12.jbrain.interpreter.CellSize;
import io.github.arkosammy12.jbrain.interpreter.MemoryOverflowBehavior;
import io.github.arkosammy12.jbrain.lexer.BrainfuckLexer;
import io.github.arkosammy12.jbrain.lexer.Token;
import io.github.arkosammy12.jbrain.parser.BrainfuckParseResult;
import io.github.arkosammy12.jbrain.parser.BrainfuckParser;
import io.github.arkosammy12.jbrain.parser.BrainfuckProgram;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "jbrain",
        mixinStandardHelpOptions = true,
        versionProvider = MavenProperties.Provider.class,
        description = "Execute the provided Brainfuck source file"
)
public class JBrain implements Callable<Integer> {

    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    @CommandLine.Option(
            names = {"-c", "--cell-size"},
            description = "Specify the size in bits of each cell. Valid values (case insensitive): ${COMPLETION-CANDIDATES}"
    )
    private CellSize cellSize = CellSize.BITS_8;

    @CommandLine.Option(
            names = {"-h", "--hash-as-debug"},
            description = "Specify whether the '#' should be interpreted as a command to output debug information",
            defaultValue = "false",
            negatable = true
    )
    private boolean hashAsDebug;

    @CommandLine.Option(
            names = {"-d", "--dynamic-memory"},
            description = "Specify whether to use dynamic or an infinite memory tape instead",
            defaultValue = "false",
            showDefaultValue = CommandLine.Help.Visibility.ON_DEMAND,
            negatable = true
    )
    private boolean dynamicMemory;

    @CommandLine.Option(
            names = {"-m", "--memory-size"},
            description = "Specify the amount of cells in the memory tape",
            defaultValue = "30000",
            showDefaultValue = CommandLine.Help.Visibility.ON_DEMAND
    )
    private Integer memorySize;

    @CommandLine.Option(
            names = {"-f", "--overflow-behavior"},
            description = "Specify what happens when the cell pointer goes out of bounds of the tape array",
            defaultValue = CommandLine.Option.NULL_VALUE
    )
    private MemoryOverflowBehavior memoryOverflowBehavior;

    @CommandLine.Option(
            names = {"-i", "--input-path"},
            description = "The input Brainfuck source file path to process or interpret",
            required = true
    )
    private Path inputFilePath;

    @Override
    public Integer call() throws Exception {
        // Validate parameters
        if (this.dynamicMemory) {
            if (this.memorySize != null) {
                throw new CommandLine.ParameterException(spec.commandLine(), "Cannot specify memory size when using dynamic memory!");
            }
            if (this.memoryOverflowBehavior != null) {
                throw new CommandLine.ParameterException(spec.commandLine(), "Cannot specify memory overflow behavior when using dynamic memory!");
            }
        } else {
            if (this.memorySize == null) {
                this.memorySize = 30000;
            }
            if (this.memoryOverflowBehavior == null) {
                this.memoryOverflowBehavior = MemoryOverflowBehavior.WRAP;
            }
        }

        if (!Files.exists(this.inputFilePath)) {
            throw new CommandLine.ParameterException(spec.commandLine(), "The specified input path '%s' does not exist!".formatted(this.inputFilePath));
        }

        Collection<String> lines = Files.readAllLines(this.inputFilePath);
        BrainfuckLexer lexer = new BrainfuckLexer(this.hashAsDebug);
        Queue<Token> tokens = lexer.lex(lines);
        BrainfuckParser parser = new BrainfuckParser();
        switch (parser.parse(tokens)) {
            case BrainfuckParseResult.Error error -> System.err.println("Failed to compile provided Brainfuck program!\n(%d:%d) %s".formatted(error.sourcePosition().row() + 1, error.sourcePosition().column() + 1, error.message()));
            case BrainfuckParseResult.Ok(BrainfuckProgram brainfuckProgram) -> {
                BrainfuckInterpreter interpreter = new BrainfuckInterpreter(30_000);
                interpreter.interpret(brainfuckProgram);
            }
        }

        return 0;
    }

    static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new JBrain());
        commandLine.setCaseInsensitiveEnumValuesAllowed(true);
        System.exit(commandLine.execute(args));
    }

}
