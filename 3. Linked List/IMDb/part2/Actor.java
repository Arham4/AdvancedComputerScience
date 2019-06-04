package org.friscoisd.k12.linkedlist.imdb.part2;

/**
 * Created by 142817 on 9/18/2018.
 */
public class Actor {
    private Actor next;
    private String name;

    public Actor() {
        next = null;
    }

    public Actor(String name) {
        this.name = name;
        next = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Actor getNext() {
        return next;
    }

    public void setNext(Actor next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Actor " + name;
    }
}
