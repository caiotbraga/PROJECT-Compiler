package org.example.semanticAnalysis;

public class SinglyLinkedList {

    SinglyListNode head;

    public Boolean isEmpty() {
        return this.head == null;
    }

    public void addFirst(String value) {
        SinglyListNode newHead = new SinglyListNode(value, this.head); // always assinging the head
        this.head = newHead;
    }

    public int size() {
        int size = 0;
        SinglyListNode currentNode = this.head;
        while (currentNode != null) {
            size++;
            currentNode = currentNode.getNext();
        }
        return size;
    }

    public SinglyListNode getHead() {
        return this.head;
    }

    public SinglyListNode search(String valueSearched) {
        if (isEmpty()) {
            return null;
        }
        SinglyListNode actualNode = this.head;
        while (actualNode != null) {
            if (valueSearched.compareToIgnoreCase(actualNode.getValue()) == 0) {
                return actualNode;
            }
            actualNode = actualNode.getNext();
        }
        return null;
    }

    public void addLast(String id) {
        SinglyListNode newTail = new SinglyListNode(id);
        if (isEmpty()) {
            this.head = newTail;
        } else {
            SinglyListNode oldTail = this.head;
            while (oldTail.getNext() != null) {
                oldTail = oldTail.getNext();
            }
            oldTail.setNext(newTail);
        }

    }
}
