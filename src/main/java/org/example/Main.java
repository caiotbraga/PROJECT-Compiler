package org.example;

import org.example.lexicalAnalysis.*;
import org.example.syntheticAnalysis.*;
public class Main {
    public static void main(String[] args) {
        Lexicon lexicon = new Lexicon("/workspace/Compiler/src/main/java/org/example/lexicalAnalysis/code.txt");
        Synthetic synthetic = new Synthetic(lexicon);
        synthetic.S();
    }
}