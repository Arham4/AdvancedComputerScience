package org.friscoisd.k12.linkedlist.imdb.part7;

/**
 * Created by 142817 on 9/18/2018.
 */
public class Node {
    private Node next;
    private Object data;

    public Node() {
        next = null;
        data = null;
    }

    public Node(Object data) {
        this.data = data;
        next = null;
    }

    public Object get() {
        return data;
    }

    public void set(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Data: " + data.toString();
    }
}
