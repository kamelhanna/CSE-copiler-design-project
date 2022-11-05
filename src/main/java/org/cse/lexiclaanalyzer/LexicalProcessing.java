package org.cse.lexiclaanalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static org.cse.lexiclaanalyzer.TokenTypes.*;

public class LexicalProcessing {
    /*private String identifier = "[a-zA-Z][a-zA-Z0-9]*";
    private String digit = "[0-9]+";
    private String comment = "[/][*][_a-zA-Z]+";
    private String Output="\'[a-zA-Z]+\'";*/

    private TokenTypes tokenTypes = new TokenTypes();
    private List keywords = tokenTypes.getKeywords();
    private List symbols = tokenTypes.getSpecialCharacters();
    private List Operation = tokenTypes.getOperators();
    private List logicalOp = tokenTypes.getLogicalOP();

    static String token;
    static Scanner inputCode;
    static String Filename = "TrueCode.txt";

    public void scanner() {
        String tokenCategory;
        int line = 1;
        try {
            inputCode = new Scanner(new File(Filename));
            while (inputCode.hasNext()) {
                token = inputCode.next();
                if (keywords.contains(token)) {
                    tokenCategory = "keyword";
                } else if (symbols.contains(token)) {
                    tokenCategory = "Symbol";
                } else if (Operation.contains(token)) {
                    tokenCategory = "operation";
                } else if (logicalOp.contains(token)) {
                    tokenCategory = "Logical Operation";
                } else if (Pattern.matches(IDENTIFIER, token)) {
                    tokenCategory = "identifier";
                } else if (Pattern.matches(COMMENT, token)) {
                    tokenCategory = "comment";
                } else if (Pattern.matches(NUMERIC_NUMBER, token)) {
                    tokenCategory = "digit";
                } else if (Pattern.matches(OUTPUT, token)) {
                    tokenCategory = "Output to the user";
                } else {
                    tokenCategory = "there an error in token !!! in line :" + line;
                }
                System.out.println("< " + token + ", " + tokenCategory + " >");
                if (token.endsWith(";")) {
                    line++;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File code not found !!");
        }
    }
}

