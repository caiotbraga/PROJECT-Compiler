package org.example;

import org.example.lexicalAnalysis.Lexicon;
import org.example.lexicalAnalysis.Token;

public class Main {
    public static void main(String[] args) {
        Lexicon lexico = new Lexicon("C:\\Users\\Administrator\\Desktop\\Projetos\\Compiler\\src\\main\\java\\org\\example\\lexicalAnalysis\\code.txt");
        Token t = null;
        while ((t = lexico.nextToken()) != null) {
            System.out.println(t.toString());
        }
    }
}