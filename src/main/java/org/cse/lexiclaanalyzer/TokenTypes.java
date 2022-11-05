package org.cse.lexiclaanalyzer;


import java.util.ArrayList;
import java.util.List;

public class TokenTypes {

    public static final String IDENTIFIER = "[a-zA-Z][a-zA-Z0-9]*";
    public static final String NUMERIC_NUMBER = "[0-9]+";
    public static final String COMMENT = "[/][*][_a-zA-Z]+";
    public static final String OUTPUT = "\'[a-zA-Z]+\'";

    private List keywords;
    private List specialCharacters;
    private List operators;
    private List logicalOperator;

    public TokenTypes() {
        keywords = new ArrayList();
        specialCharacters = new ArrayList();
        operators = new ArrayList();
        logicalOperator = new ArrayList();

        keywords.add("if");keywords.add("else");keywords.add("elseif");
        keywords.add("for");keywords.add("while");keywords.add("do");
        keywords.add("return");keywords.add("public");keywords.add("privet");
        keywords.add("protect");keywords.add("package");keywords.add("new");
        keywords.add("int");keywords.add("double");keywords.add("float");
        keywords.add("static");keywords.add("const");keywords.add("char");
        keywords.add("goto");keywords.add("boolean");keywords.add("long");
        keywords.add("void");keywords.add("this");keywords.add("try");
        keywords.add("catch");keywords.add("true");keywords.add("print");
        keywords.add("enter");

        specialCharacters.add("!");
        specialCharacters.add('&');
        specialCharacters.add('^');
        specialCharacters.add('%');
        specialCharacters.add('$');
        specialCharacters.add('#');
        specialCharacters.add('@');
        specialCharacters.add('?');
        specialCharacters.add('~');
        specialCharacters.add("(*"); //begin the condition
        specialCharacters.add("*)"); //end the condition
        specialCharacters.add("{*"); //begin the loop
        specialCharacters.add("*}"); //end for loop
        specialCharacters.add(";");
        specialCharacters.add("'"); //use to print

        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("=");

        logicalOperator.add("||");
        logicalOperator.add("&&");
        logicalOperator.add("==");
        logicalOperator.add("<=");
        logicalOperator.add(">=");
        logicalOperator.add(">");
        logicalOperator.add("<");
    }

    public List getKeywords() {
        return keywords;
    }

    public List getSpecialCharacters() {
        return specialCharacters;
    }

    public List getOperators() {
        return operators;
    }

    public List getLogicalOP() {
        return logicalOperator;
    }
}
