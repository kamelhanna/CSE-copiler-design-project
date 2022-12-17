package org.cse.expressionTree;

public class ExponentiationExpressionNode implements ExpressionNode{
    private ExpressionNode base;
    private ExpressionNode exponent;

    public ExponentiationExpressionNode(ExpressionNode base,
                                        ExpressionNode exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public int getType() {
        return ExpressionNode.EXPONENTIATION_NODE;
    }

    public double getValue() {
        return Math.pow(base.getValue(), exponent.getValue());
    }
}
