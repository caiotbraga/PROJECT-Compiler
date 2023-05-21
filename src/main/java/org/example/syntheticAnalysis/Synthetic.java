package org.example.syntheticAnalysis;

import org.example.lexicalAnalysis.Lexicon;
import org.example.lexicalAnalysis.Token;

public class synthetic {
    private Lexicon lexicon;
    private Token token;

    public synthetic(Lexicon lex){
        this.lexicon = lex;
    }

    public void s() {
        this.token = this.lexicon.nextToken();
        if(!token.getLexeme().equals("int")){
            throw new RuntimeException("You first code word shall be *int* !");
        }
        this.token = this.lexicon.nextToken();
        if(!token.getLexeme().equals("main")){
            throw new RuntimeException("You second code word shall be *main* !");
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
        this.block();
        if(this.token.getLexeme().equals("$")){
            System.out.println("Well done! Your code don't have erros!");
        }else{
            throw new RuntimeException("ERRO! Error near the end of code!");
        }
    }

    private void block() {
        if(!token.getLexeme().equals("{")){
            throw new RuntimeException("You need open the method declaration with *{* !");
        }
        this.token = this.lexicon.nextToken();
        this.variableDec();
        this.command();
        if(!token.getLexeme().equals("}")){
            throw new RuntimeException("You need close the method declaration with *}* !");
        }
        this.token = this.lexicon.nextToken();
    }

    private void variableDec() {
        if(equalsType(token.getLexeme())){
            this.token = this.lexicon.nextToken();
            if(!(token.getType() == Token.IDENTIFIER_TYPE)){
                throw new RuntimeException("ERROR! It shall be an identifier type near"+this.token.getLexeme());
            }else{
                if(!(token.getLexeme().equals(";"))){
                    throw new RuntimeException("ERROR! You forgot to put the ; after the declaration of the variable near "+this.token.getLexeme());
                }
                this.token = this.lexicon.nextToken();
                if(equalsType(token.getLexeme())){
                    this.variableDec();
                }
            }
        }else{
            throw new RuntimeException("ERROR! It shall be an declaration type!");
        }
        //     this.assignment();
        // }else if(token.getLexeme().equals("double") || token.getLexeme().equals("int") ||    //TIRAR DUVIDA SE PODE SER TOKEN.TIPO OU TEM Q SER EQUALS("EXEMPLO")
        //          token.getLexeme().equals("char")   || token.getLexeme().equals("String") ){
        //     this.declaration();
        // }else{
        //     throw new RuntimeException("ERROR! Something is wrong when you tried to declarate your variable near " +token.getLexeme());
        // }
    }

    private void command() { //pode entrar em atribuição que então vai precisar verificar se o primeiro é do tipo identificador 
        if(this.token.getType() == Token.IDENTIFIER_TYPE){
            this.basicCommand();
        }else if(this.token.getLexeme().equals("while")){
            this.interaction();
        }else{
            this.selectionStructure();
        }
        
    }

    private void selectionStructure() {
    }

    private void interaction() {
        this.token = this.lexicon.nextToken();
        if(token.getLexeme().equals("()")){
            throw new RuntimeException("");
        }
    }

    private void basicCommand() {
        
    }

    private void CS() {
        if(token.getLexeme().equals("double") || token.getLexeme().equals("int") || 
        token.getLexeme().equals("char")   || token.getLexeme().equals("String") || token.getType() == Token.IDENTIFIER_TYPE){ 
            this.variableDec();                                                                                                                   
            this.CS();
        }else{
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
    
    private boolean equalsType(String lexeme){
        if(lexeme.equals("int") || lexeme.equals("double") || lexeme.equals("char") || lexeme.equals("float") || lexeme.equals("String") ){
            return true;
        }
        return false;
    }
}
