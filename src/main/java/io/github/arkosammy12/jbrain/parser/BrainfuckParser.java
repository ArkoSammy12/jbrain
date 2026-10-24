package io.github.arkosammy12.jbrain.parser;

import io.github.arkosammy12.jbrain.lexer.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiFunction;

public final class BrainfuckParser {

    public BrainfuckParseResult parse(Queue<Token> tokens) {
        try {
            BrainfuckParserContext context = new BrainfuckParserContext(tokens);
            for (BrainfuckNode node : this.parseTopLevel(context)) {
                context.addNode(node);
            }
            return context.toProgram();
        } catch (BrainfuckParseException brainfuckParseException) {
            return brainfuckParseException.toError();
        }
    }

    private Collection<BrainfuckNode> parseTopLevel(BrainfuckParserContext context) throws BrainfuckParseException {
        Collection<BrainfuckNode> nodes = new ArrayList<>();
        Optional<Token> token;
        while ((token = context.pollToken()).isPresent()) {
            nodes.add(this.parseToken(context, token.get()));
        }
        return nodes;
    }

    private BrainfuckNode parseToken(BrainfuckParserContext context, Token token) throws BrainfuckParseException {
        return switch (token) {
            case PlusToken plusToken -> this.parseTokenRun(context, plusToken, IncrementCellValueNode::new);
            case MinusToken minusToken -> this.parseTokenRun(context, minusToken, DecrementCellValueNode::new);
            case RightAngledBracket rightAngledBracketToken -> this.parseTokenRun(context, rightAngledBracketToken, IncrementCellPointerNode::new);
            case LeftAngledBracket leftAngledBracket -> this.parseTokenRun(context, leftAngledBracket, DecrementCellPointerNode::new);
            case LeftSquareBracketToken leftSquareBracketToken -> this.parseLoop(context, leftSquareBracketToken);
            case RightSquareBracket rightSquareBracket -> throw new BrainfuckParseException("Unmatched ']'!", rightSquareBracket.getSourcePosition());
            case PeriodToken periodToken -> new OutputCellValue(periodToken);
            case CommaToken commaToken -> new InputCellValue(commaToken);
            case HashToken hashToken -> new OutputDebugNode(hashToken);
        };
    }

    private <T extends Token> BrainfuckInstructionNode<T> parseTokenRun(BrainfuckParserContext context, T token, BiFunction<T, Integer, BrainfuckInstructionNode<T>> constructor) {
        int count = 1;
        Optional<Token> next;
        while (count < Integer.MAX_VALUE && (next = context.peekToken()).isPresent() && token.isSameTypeAs(next.get())) {
            context.pollToken();
            count++;
        }
        return constructor.apply(token, count);
    }

    private BrainfuckNode parseLoop(BrainfuckParserContext context, LeftSquareBracketToken leftSquareBracketToken) throws BrainfuckParseException {
        Collection<BrainfuckNode> nodes = new ArrayList<>();
        Optional<Token> optionalToken;
        while ((optionalToken = context.pollToken()).isPresent()) {
            Token token = optionalToken.get();
            if (token instanceof RightSquareBracket) {
                return new BrainfuckLoopNode(nodes);
            } else {
                nodes.add(this.parseToken(context, token));
            }
        }
        throw new BrainfuckParseException("Unmatched '['!", leftSquareBracketToken.getSourcePosition());
    }

}
