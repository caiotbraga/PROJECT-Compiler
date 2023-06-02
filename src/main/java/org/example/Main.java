package org.example;

import org.example.lexicalAnalysis.*;
import org.example.syntheticAnalysis.*;
public class Main {
    public static void main(String[] args) {
        Lexicon lexicon = new Lexicon("C:\\Users\\André\\Desktop\\compilador\\Compiler\\src\\main\\java\\org\\example\\code.txt");
        Synthetic synthetic = new Synthetic(lexicon);
        synthetic.s();
        
    }
}