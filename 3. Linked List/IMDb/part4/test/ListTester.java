package org.friscoisd.k12.linkedlist.imdb.part4.test;

import org.friscoisd.k12.linkedlist.imdb.part4.actor.Actor;
import org.friscoisd.k12.linkedlist.imdb.part4.LinkedList;

/**
 * Created by 142817 on 9/24/2018.
 */

public class ListTester {
    public static void main(String[] args) {
        LinkedList actors = new LinkedList();
        for (int i = 0; i < 5; i++) {
            actors.add(new Actor("kms" + i));
        }
        for (int i = 0; i < actors.size(); i++) {
            System.out.println(actors.get(i));
        }
        System.out.println(((Actor) actors.get(0)).getName());

    }
}
