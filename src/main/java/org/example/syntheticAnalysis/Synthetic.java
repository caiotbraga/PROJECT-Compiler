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
        this.token = this.lexicon.nextToken();
    }

    private void CS() {
        if(token.getLexeme().equals("double") || token.getLexeme().equals("int") ||    //TIRAR DUVIDA SE PODE SER TOKEN.TIPO OU TEM Q SER EQUALS("EXEMPLO")
        token.getLexeme().equals("char")   || token.getLexeme().equals("String") || token.getType() == Token.IDENTIFIER_TYPE){ //eu(caio) acho que nao pois se o lexema for igual a 
            this.B();                                                                                                                   //int, double... ele possuira o token do seu tipo
            this.CS();
        }else{
        }
    }

    private void B() {
        if(token.getType() == token.IDENTIFIER_TYPE){
            this.assignment();
        }else if(token.getLexeme().equals("double") || token.getLexeme().equals("int") ||    //TIRAR DUVIDA SE PODE SER TOKEN.TIPO OU TEM Q SER EQUALS("EXEMPLO")
                 token.getLexeme().equals("char")   || token.getLexeme().equals("String") ){
            this.declaration();
        }else{
            throw new RuntimeException("ERROR! Something is wrong when you tried to declarate your variable near " +token.getLexeme());
        }
    }

    private void declaration() { //no código do professor o primeiro if é redundante
        this.token = this.lexicon.nextToken();
        if(!(token.getType() == Token.IDENTIFIER_TYPE)){
            throw new RuntimeException("ERROR! You shall declarate your identifier after his type! near"+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        if(!(token.getLexeme().equals(";"))){
            throw new RuntimeException("ERROR! You forgot to put the ; after the declaration of the variable near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
    }

    private void assignment() {
        if(!(token.getType() == Token.IDENTIFIER_TYPE)){
            throw new RuntimeException("ERROR! You forgot to put the assignment operator near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        if(!(token.getType() == Token.ASSIGNMENT_OPERATOR_TYPE)){
            throw new RuntimeException("ERROR! You forgot the assignment operator near"+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        this.E();
        if(!this.token.getLexeme().equals(";")){
            throw new RuntimeException("ERROR! Assignment error near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken(); 
    }

    private void E(){
        this.T();
        this.El();
    }
    
    private void El(){
        if(this.token.getType() == Token.ARITHMETIC_OPERATOR_TYPE){
            this.OP();
            this.T();
            this.El();
        }else{        
        }
    }
    
    private void T(){
        if(this.token.getType() == Token.IDENTIFIER_TYPE || 
                  this.token.getType() == Token.INT_TYPE || this.token.getType() == Token.DOUBLE_TYPE){
            this.token = this.lexicon.nextToken();
        }else{
            throw new RuntimeException("ERROR! It should be a number or an identifier near"+this.token.getLexeme());
        }
    }
    
    private void OP(){
        if(this.token.getType() == Token.ARITHMETIC_OPERATOR_TYPE){
            this.token = this.lexicon.nextToken();
        }else{
            throw new RuntimeException("ERROR! It should be an arithmetic operator type near"+this.token.getLexeme());
        }
    }
    
}
