package org.example.syntheticAnalysis;

import org.example.lexicalAnalysis.Lexicon;
import org.example.lexicalAnalysis.Token;
import org.example.semanticAnalysis.SinglyLinkedList;
import org.example.semanticAnalysis.SinglyListNode;

public class Synthetic {
    private Lexicon lexicon;
    private Token token;
    private SinglyLinkedList[] typeList = new SinglyLinkedList[10];
    private int i = 0;

    public Synthetic(Lexicon lex){
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
        this.block();
        if(this.token.getLexeme().equals("$")){
            System.out.println("Well done! Your code don't have erros!");
        }else{
            throw new RuntimeException("ERRO! Error near the end of code!");
        }
    }

    private void block() {
        this.token = this.lexicon.nextToken();
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
        String type = getTypeUsingToken(token.getLexeme());
        if(equalsType(token.getLexeme())){
            this.token = this.lexicon.nextToken();
            if(!(token.getType() == Token.IDENTIFIER_TYPE)){
                throw new RuntimeException("ERROR! It shall be an identifier type near"+this.token.getLexeme());
            }else{
                typeList[i] = new SinglyLinkedList();
                typeList[i].addFirst(token.getLexeme());
                typeList[i].getHead().setNext(new SinglyListNode(type));
                i++;
                this.token = this.lexicon.nextToken();
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
    }

    private void command() { //pode entrar em atribuição que então vai precisar verificar se o primeiro é do tipo identificador 
        if(this.token.getLexeme().equals("while")){
            this.interaction();
        }
        if(this.token.getLexeme().equals("if")){
            this.selectionStructure();
        }
        if(this.token.getType() == Token.IDENTIFIER_TYPE || equalsType(token.getLexeme()) || token.getLexeme().equals("while") || token.getLexeme().equals("if")){
            this.basicCommand();
        }
        if(this.token.getLexeme().equals("while") || this.token.getLexeme().equals("if") || this.token.getType() == Token.IDENTIFIER_TYPE || equalsType(token.getLexeme()) ){
            this.command();
        }
    }

    //if 
    private void selectionStructure() {
        this.token = this.lexicon.nextToken();
        if(!(token.getLexeme().equals("("))){
            throw new RuntimeException("ERROR! It shall be an ( near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        this.relationalExpression();
        if(!(token.getLexeme().equals(")"))){
            throw new RuntimeException("ERROR! It shall be an ) near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        if(!(token.getLexeme().equals("{"))){
            throw new RuntimeException("ERROR! It shall be an { near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        this.command();
        if(!(token.getLexeme().equals("}"))){
            throw new RuntimeException("ERROR! It shall be an } near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        if(token.getLexeme().equals("else")){
            this.token = this.lexicon.nextToken();
            if(!(token.getLexeme().equals("{"))){
                throw new RuntimeException("ERROR! It shall be an { near "+this.token.getLexeme());
            }
            this.token = this.lexicon.nextToken();
            this.command();
            if(!(token.getLexeme().equals("}"))){
                throw new RuntimeException("ERROR! It shall be an } near "+this.token.getLexeme());
            }
            this.token = this.lexicon.nextToken();
        }
    }

    //while
    private void interaction() {
        this.token = this.lexicon.nextToken();
        if(!(token.getLexeme().equals("("))){
            throw new RuntimeException("ERROR! It shall be an ( near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        this.relationalExpression();
        if(!(token.getLexeme().equals(")"))){
            throw new RuntimeException("ERROR! It shall be an ) near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        if(!(token.getLexeme().equals("{"))){
            throw new RuntimeException("ERROR! It shall be an { near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
        this.command();
        if(!(token.getLexeme().equals("}"))){
            throw new RuntimeException("ERROR! It shall be an } near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken();
    }

    private void relationalExpression() {
        this.arithmeticExpression();
        this.relationalOperator();
        this.arithmeticExpression();
    }

    private void basicCommand() {
        this.token = this.lexicon.nextToken();
        if(this.token.getLexeme().equals("=")){
            this.assignment();
        }else{
            this.block();
        }
    }

    private void assignment() {
        this.token = this.lexicon.nextToken();
        this.arithmeticExpression();
        if(!this.token.getLexeme().equals(";")){
            throw new RuntimeException("ERROR! Assignment error near "+this.token.getLexeme());
        }
        this.token = this.lexicon.nextToken(); 
    }

    private void arithmeticExpression(){
        this.term();
        if(this.token.getLexeme().equals("+") || this.token.getLexeme().equals("-")){
            this.sum();
            this.arithmeticExpression();
        }
    }
    
    private void sum() {
        this.token = this.lexicon.nextToken(); 
    }
    
    private void term(){
        this.factor();
        if(this.token.getLexeme().equals("*") || this.token.getLexeme().equals("/")){
            this.mult();
            this.term();
        }
    }
    
    private void mult() {
        this.token = this.lexicon.nextToken(); 
    }

    private void factor() {
        if(this.token.getType() == Token.DOUBLE_TYPE || this.token.getType() == Token.INT_TYPE || this.token.getType() == Token.IDENTIFIER_TYPE){
            this.token = this.lexicon.nextToken(); 
        }else{
            this.arithmeticExpression();
        }
    }

    private void relationalOperator(){
        if(this.token.getType() == Token.RELATIONAL_OPERATOR_TYPE){
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

    public String getTypeUsingToken(String type){
        if(type.compareTo("int") == 0){
            return "INT";
        }else if(type.compareTo("Double") == 0){
            return "DOUBLE";
        }else if(type.compareTo("char") == 0){
            return "CHAR";
        }else if(type.compareTo("String") == 0){
            return "STRING";
        }else{
            throw new RuntimeException("ERROR!");
        }
    }
}
