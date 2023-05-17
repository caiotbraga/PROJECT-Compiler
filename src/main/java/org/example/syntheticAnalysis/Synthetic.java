package org.example.syntheticAnalysis;

import org.example.lexicalAnalysis.Lexicon;
import org.example.lexicalAnalysis.Token;

public class Synthetic {
    private Lexicon lexicon;
    private Token token;

    public Synthetic(Lexicon lex){
        this.lexicon = lex;
    }

    public void S() {
        this.token = this.lexicon.nextToken();
        if(!token.getLexeme().equals("main")){
            throw new RuntimeException("You first code word shall be *main* !");
        }
        this.token = this.lexicon.nextToken();
        if(!token.getLexeme().equals("(")){
            throw new RuntimeException("After main you need put the parentheses *(* !");
        }
        this.token = this.lexicon.nextToken();
        if(!token.getLexeme().equals(")")){
            throw new RuntimeException("After main you need close the parentheses *(* !");
        }
        this.token = this.lexicon.nextToken();
        this.A();
        if(this.token.getLexeme().equals("$")){
            System.out.println("Well done! Your code don't have erros!");
        }else{
            throw new RuntimeException("ERRO! Error near the end of code!");
        }
    }

    private void A() {
        if(!token.getLexeme().equals("{")){
            throw new RuntimeException("You need open the method declaration with *{* !");
        }
        this.token = this.lexicon.nextToken();
        this.CS();
        if(!token.getLexeme().equals("}")){
            throw new RuntimeException("You need close the method declaration with *}* !");
        }

    }

    private void CS() {
        if(token.getType() == Token.DOUBLE_TYPE || token.getType() == Token.INT_TYPE ||    //TIRAR DUVIDA SE PODE SER TOKEN.TIPO OU TEM Q SER EQUALS("EXEMPLO")
           token.getType() == Token.CHAR_TYPE   || token.getType() == Token.STRING_TYPE || token.getType() == Token.IDENTIFIER_TYPE){ //eu(caio) acho que nao pois se o lexema for igual a 
            this.B();                                                                                                                   //int, double... ele possuira o token do seu tipo
            this.CS();
        }else{
        }
    }

    private void B() {
        if(token.getType() == token.IDENTIFIER_TYPE){
            this.assignment();
        }else if(token.getType() == Token.CHAR_TYPE ||
                 token.getType() == Token.DOUBLE_TYPE || 
                 token.getType() == Token.INT_TYPE ||
                 token.getType() == Token.STRING_TYPE ){
            this.declaration();
        }else{
            throw new 
        }
    }

    private void declaration() { //no código do professor o primeiro if é redundante
        if(!(token.getType() == Token.CHAR_TYPE ||
                 token.getType() == Token.DOUBLE_TYPE || 
                 token.getType() == Token.INT_TYPE ||
                 token.getType() == Token.STRING_TYPE))
    }

    private void assignment() {

    }
    
}
