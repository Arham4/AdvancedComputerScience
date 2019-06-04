package org.friscoisd.k12.linkedlist.imdb.part8.imdb.assets;

/**
 * Created by 142817 on 9/18/2018.
 */
public class Actor {
    private String name;

    public Actor() {
        name = null;
    }

    public Actor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Actor " + name;
    }
}
