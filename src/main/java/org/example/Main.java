package org.example;

import org.example.lexicalAnalysis.*;
import org.example.syntheticAnalysis.*;
public class Main {
    public static void main(String[] args) {
        Lexicon lexicon = new Lexicon("C:\\Users\\11159819432\\Desktop\\COMP\\Compiler\\src\\main\\java\\org\\example\\lexicalAnalysis\\code.txt");
        Token t = null;
        while ((t = lexicon.nextToken()) != null) {
            System.out.println(t.toString());
        }
    }
}