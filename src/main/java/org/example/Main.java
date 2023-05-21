package org.example;

import org.example.lexicalAnalysis.*;
import org.example.syntheticAnalysis.*;
public class Main {
    public static void main(String[] args) {
        Lexicon lexicon = new Lexicon("C:\\Users\\Administrator\\Desktop\\Projetos\\Compiler\\src\\main\\java\\org\\example\\code.txt");
        synthetic synthetic = new synthetic(lexicon);
        synthetic.s();
    }
}