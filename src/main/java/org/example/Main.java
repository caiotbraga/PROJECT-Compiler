package org.example;

import org.example.lexicalAnalysis.*;
import org.example.syntheticAnalysis.*;
public class Main {
    public static void main(String[] args) {
        Lexicon lexicon = new Lexicon("/Users/caiotbraga/Desktop/Projetos/Compiler/Compiler/src/main/java/org/example/code.txt");
        synthetic synthetic = new synthetic(lexicon);
        synthetic.s();
    }
}