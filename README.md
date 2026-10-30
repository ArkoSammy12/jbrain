# jbrain

A Brainfuck interpreter and transpiler written in Java

## Command-line Usage

Make sure to have a Java Development Kit (JDK) targeting Java 25 or later to run the built `.jar` file.

You can run the assembler with the `java -jar jbrain-x.y.z.jar <args>` command. It is recommended to alias the JVM `-jar` invocation.

The CLI options are as follows:

```
Usage: jbrain [-dhtV] [-c=<cellSize>] [-f=<memoryOverflowBehavior>]
              -i=<inputFilePath> [-m=<memorySize>]
Execute the provided Brainfuck source file
  -c, --cell-size=<cellSize>
                  Specify the size in bits of each cell. Valid values (case
                    insensitive): BITS_8, BITS_16, BITS_32
  -d, --[no-]dynamic-memory
                  Specify whether to use dynamic or an infinite memory tape
                    instead
  -f, --overflow-behavior=<memoryOverflowBehavior>
                  Specify what happens when the cell pointer goes out of bounds
                    of the tape array
  -h, --help      Show this help message and exit.
  -i, --input-path=<inputFilePath>
                  The input Brainfuck source file path to process or interpret
  -m, --memory-size=<memorySize>
                  Specify the amount of cells in the memory tape
  -t, --[no-]trace-wish-hash
                  Specify whether the '#' should be interpreted as a command to
                    output debug information
  -V, --version   Print version information and exit.
```

## Building

*jbrain* is written in Java 25, so a Java Development Kit (JDK) targeting Java 25 or later to build this project.

Maven is used as the build and dependency management solution, though a wrapper is provided to avoid a local installation.
To build the project, clone or download the repository and run the following from the top-level directory:

### Windows

```
.\mvnw clean package
```

### macOS/Linux

```
./mvnw clean package
```

An output `.jar` should have then been generated in `/target/jbrain-x.y.z.jar`.
To skip the automated tests, append the `-DskipTests` to the build command.

## License
This project is licensed under the [MIT License](LICENSE).