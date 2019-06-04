package org.friscoisd.k12.linkedlist.imdb.part7;

/**
 * Created by 142817 on 9/18/2018.
 */

public class LinkedList {
    private Node head;
    private int count;

    public LinkedList() {
        head = null;
        count = 0;
    }

    public void add(Object actor) {
        Node oldHead = head;
        head = new Node(actor);
        head.setNext(oldHead);
        count++;
    }

    public Object get(int index) {
        Node dummy = head;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return dummy.get();
            }
            dummy = dummy.getNext();
        }
        return null;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder outputBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            outputBuilder.append(get(i).toString()).append(i != count - 1 ? " -> " : "");
        }
        return outputBuilder.toString();
    }
}
