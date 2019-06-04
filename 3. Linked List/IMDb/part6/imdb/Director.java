package org.friscoisd.k12.linkedlist.imdb.part6.imdb;

/**
 * Created by 142817 on 9/18/2018.
 */
public class Director {
    private String name;

    public Director() {
        name = null;
    }

    public Director(String name) {
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
        return "Director " + name;
    }
}
