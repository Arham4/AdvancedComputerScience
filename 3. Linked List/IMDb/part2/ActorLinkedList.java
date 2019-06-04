package org.friscoisd.k12.linkedlist.imdb.part2;

/**
 * Created by 142817 on 9/18/2018.
 */
public class ActorLinkedList {
    private Actor head;
    private int count;

    public ActorLinkedList() {
        head = null;
        count = 0;
    }

    public void add(Actor actor) {
        Actor oldHead = head;
        head = actor;
        head.setNext(oldHead);
        count++;
    }

    public Actor get(int index) {
        Actor dummy = head;
        for (int i = 0; i < count; i++) {
            if (i == index) {
                return dummy;
            }
            dummy = dummy.getNext();
        }
        return null;
    }

    public int size() {
        return count;
    }
}
