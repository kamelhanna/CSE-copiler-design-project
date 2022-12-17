package org.cse;

import org.cse.expressionTree.ExpressionNode;
import org.cse.lexical.Token;
import org.cse.lexical.Tokenizer;
import org.cse.parser.Parser;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Add tokens
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.add("[+-]", Token.PLUSMINUS);
        tokenizer.add("[*/]", Token.MULTDIV);
        tokenizer.add("\\^", Token.RAISED);

        String functions = "sin|cos|tan|asin|acos|atan|sqrt|exp|ln|log|log2|abs";
        tokenizer.add("(" + functions + ")(?!\\w)", Token.FUNCTION);

        tokenizer.add("\\(", Token.OPEN_BRACKET);
        tokenizer.add("\\)", Token.CLOSE_BRACKET);
        tokenizer.add("(?:\\d+\\.?|\\.\\d)\\d*(?:[Ee][-+]?\\d+)?", Token.NUMBER);
        tokenizer.add("[a-zA-Z]\\w*", Token.VARIABLE);

        System.out.println("Enter Expression");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        tokenizer.tokenize(input);

        LinkedList<Token> tokens = tokenizer.getTokens();

        for (Token tok : tokenizer.getTokens()) {
            System.out.println("" + tok.token + " " + tok.sequence);
        }

        Parser parser = new Parser();
        ExpressionNode expressionNode = parser.parse(tokens);

        System.out.println("The value of the expression is " + expressionNode.getValue());
    }
}