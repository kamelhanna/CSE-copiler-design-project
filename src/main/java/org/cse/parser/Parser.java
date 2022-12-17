package org.cse.parser;

import org.cse.expressionTree.*;
import org.cse.lexical.Token;

import java.util.LinkedList;

public class Parser {
    LinkedList<Token> tokens;
    Token lookahead;

    public ExpressionNode parse(LinkedList<Token> tokens)
    {
        this.tokens = (LinkedList<Token>) tokens.clone();
        lookahead = this.tokens.getFirst();

        ExpressionNode expressionNode = expression();

        if (lookahead.token != Token.EPSILON)

            throw new RuntimeException("Unexpected symbol %s found");

        return expressionNode;
    }

    private void nextToken()
    {
        tokens.pop();
        // at the end of input we return an epsilon token
        if (tokens.isEmpty())
            lookahead = new Token(Token.EPSILON, "", -1);
        else
            lookahead = tokens.getFirst();
    }

    private ExpressionNode expression()
    {
        // expression -> signed_term sum_op
        ExpressionNode expr = signedTerm();
        return sumOp(expr);
    }

    private ExpressionNode sumOp(ExpressionNode expression)
    {
        if (lookahead.token == Token.PLUSMINUS)
        {
            // sum_op -> PLUSMINUS term sum_op
            AdditionExpressionNode sum;
            if (expression.getType() == ExpressionNode.ADDITION_NODE)
                sum = (AdditionExpressionNode)expression;
            else
                sum = new AdditionExpressionNode(expression, true);

            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = term();
            sum.add(t, positive);

            return sumOp(sum);
        }
        else
        {
            // sum_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode signedTerm()
    {
        if (lookahead.token == Token.PLUSMINUS)
        {
            // signed_term -> PLUSMINUS term
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = term();
            if (positive)
                return t;
            else
                return new AdditionExpressionNode(t, false);
        }
        else
        {
            // signed_term -> term
           return term();
        }
    }

    private ExpressionNode term()
    {
        // term -> factor term_op
        ExpressionNode f = factor();
        return termOp(f);
    }

    private ExpressionNode termOp(ExpressionNode expression)
    {
        if (lookahead.token == Token.MULTDIV)
        {
            // term_op -> MULTDIV signed_factor term_op
            MultiplicationExpressionNode prod;

            if (expression.getType() == ExpressionNode.MULTIPLICATION_NODE)
                prod = (MultiplicationExpressionNode)expression;
            else
                prod = new MultiplicationExpressionNode(expression, true);

            boolean positive = lookahead.sequence.equals("*");
            nextToken();
            ExpressionNode f = signedFactor();
            prod.add(f, positive);

            return termOp(prod);
        }
        else
        {
            // term_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode signedFactor()
    {
        if (lookahead.token == Token.PLUSMINUS)
        {
            // signed_factor -> PLUSMINUS factor
            boolean positive = lookahead.sequence.equals("+");
            nextToken();
            ExpressionNode t = factor();

            if (positive)
                return t;
            else
                return new AdditionExpressionNode(t, false);
        }
        else
        {
            // signed_factor -> factor
            return factor();
        }
    }

    private ExpressionNode factor()
    {
        // factor -> argument factor_op
        ExpressionNode a = argument();
        return factorOp(a);
    }

    private ExpressionNode factorOp(ExpressionNode expression)
    {
        if (lookahead.token == Token.RAISED)
        {
            // factor_op -> RAISED expression
            nextToken();
            ExpressionNode exponent = signedFactor();
            return new ExponentiationExpressionNode(expression, exponent);
        }
        else
        {
            // factor_op -> EPSILON
            return expression;
        }
    }

    private ExpressionNode argument()
    {
        if (lookahead.token == Token.FUNCTION)
        {
            // argument -> FUNCTION argument
            int function = FunctionExpressionNode.stringToFunction(lookahead.sequence);
            nextToken();
            ExpressionNode expr = argument();
            return new FunctionExpressionNode(function, expr);
        }
        else if (lookahead.token == Token.OPEN_BRACKET)
        {
            // argument -> OPEN_BRACKET sum CLOSE_BRACKET
            nextToken();
            ExpressionNode expr = expression();

            if (lookahead.token != Token.CLOSE_BRACKET)
                throw new RuntimeException("Closing brackets expected and "
                        + lookahead.sequence + " found instead");

            nextToken();
            return expr;
        }
        else
        {
            // argument -> value
           return value();
        }
    }

    private ExpressionNode value()
    {
        if (lookahead.token == Token.NUMBER)
        {
            // value -> NUMBER
            ExpressionNode expr = new ConstantExpressionNode(lookahead.sequence);
            nextToken();
            return expr;
        }
        else if (lookahead.token == Token.VARIABLE)
        {
            // value -> VARIABLE
            ExpressionNode expr = new VariableExpressionNode(lookahead.sequence);
            nextToken();
            return expr;
        }
        else
        {
            throw new RuntimeException("Unexpected symbol "+lookahead.sequence+" found");
        }
    }

}

