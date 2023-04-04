package org.example.lexicalAnalysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lexicon {
    private char[] content;
    private int contentIndex;

    public Lexicon(String sourceCodePath) {
        try {
            String strContent;
            strContent = new String(Files.readAllBytes(Paths.get(sourceCodePath)));
            this.content = strContent.toCharArray();
            this.contentIndex = 0;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Retorna próximo char
    private char nextChar() {
        return this.content[this.contentIndex++];
    }

    //Verifica existe próximo char ou chegou ao final do código fonte
    private boolean hasNextChar() {
        return contentIndex < this.content.length;
    }

    //Retrocede o índice que aponta para o "char da vez" em uma unidade
    private void back() {
        this.contentIndex--;
    }

    //Identificar se char é letra
    private boolean isLetra(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    //Identificar se char é dígito
    private boolean isDigito(char c) {
        return (c >= '0') && (c <= '9');
    }

    public Token nextToken() { //state machine
        Token token = null;
        char c;
        int state = 0;

        StringBuffer lexema = new StringBuffer();
        while (this.hasNextChar()) {
            c = this.nextChar();
            switch (state) {
                case 0:
                    if (c == ' ' || c == '\t' || c == '\n' || c == '\r') {
                        state = 0;
                    } else if (this.isLetra(c) || c == '_') {
                        lexema.append(c);
                        state = 1;
                    } else if (this.isDigito(c)) {
                        lexema.append(c);
                        state = 2;
                    } else if (c == ')' ||
                            c == '(' ||
                            c == '{' ||
                            c == '}' ||
                            c == ',' ||
                            c == ';') {
                        lexema.append(c);
                        state = 5;
                    } else if (c == '\'') {
                        lexema.append(c);
                        state = 6;
                    } else if (c == '$') {
                        lexema.append(c);
                        state = 99;
                        this.back();
                    } else {
                        lexema.append(c);
                        throw new RuntimeException("Erro: token inválido \"" + lexema.toString() + "\"");
                    }
                    break;
                case 1:
                    if (this.isLetra(c) || this.isDigito(c) || c == '_') {
                        lexema.append(c);
                        state = 1;
                    } else {
                        this.back();
                        return new Token(lexema.toString(), Token.IDENTIFIER_TYPE);
                    }
                    break;
                case 2:
                    if (this.isDigito(c)) {
                        lexema.append(c);
                        state = 2;
                    } else if (c == '.') {
                        lexema.append(c);
                        state = 3;
                    } else {
                        this.back();
                        return new Token(lexema.toString(), Token.INT_TYPE);
                    }
                    break;
                case 3:
                    if (this.isDigito(c)) {
                        lexema.append(c);
                        state = 4;
                    } else {
                        throw new RuntimeException("ERROR: invalid float number! --> \"" + lexema.toString() + "\"");
                    }
                    break;
                case 4:
                    if (this.isDigito(c)) {
                        lexema.append(c);
                        state = 4;
                    } else {
                        this.back();
                        return new Token(lexema.toString(), Token.DOUBLE_TYPE);
                    }
                    break;
                case 5:
                    this.back();
                    return new Token(lexema.toString(), Token.SPECIAL_CHARACTER_TYPE);
                case 6:
                    if(this.isDigito(c) || this.isLetra(c)){
                        lexema.append(c);
                        state = 7;
                    }else{
                        throw new RuntimeException("ERROR: Incorrect char format! --> \"" + lexema.toString() + "\"");
                    }
                    break;
                case 7:
                    if(c == '\''){
                        this.back();
                        lexema.append(c);
                        return new Token(lexema.toString(), Token.CHAR_TYPE);
                    }else{
                        throw new RuntimeException("ERROR: Incorrect char format! --> \"" + lexema.toString() + "\"");
                    }
                case 99:
                    return new Token(lexema.toString(), Token.END_CODE_TYPE);
            }
        }
        return token;
    }
}
