package org.example.semanticAnalysis;

public class SinglyListNode {

    private SinglyListNode next;
    private String value;

    public SinglyListNode(String value, SinglyListNode next) {
        this.next = next;
        this.value = value;
    }

    public SinglyListNode(String value) {
        this.next = null;
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public SinglyListNode getNext() {
        return this.next;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNext(SinglyListNode next) {
        this.next = next;
    }

   @Override
   public boolean equals(Object obj) {
       if (obj instanceof SinglyListNode){
            if(this.value.equals(value)){
                return true;
            }
       }
       return false;
   }
}
