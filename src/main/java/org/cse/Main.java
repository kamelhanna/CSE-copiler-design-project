package org.cse;

import org.cse.lexiclaanalyzer.LexicalProcessing;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        LexicalProcessing lexicalProcessing = new LexicalProcessing();
        lexicalProcessing.scanner();
    }
}