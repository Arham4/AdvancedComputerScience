package org.friscoisd.k12.linkedlist.imdb.part2;

/**
 * Created by 142817 on 9/24/2018.
 */
public class ListTester {
    public static void main(String[] args) {
        ActorLinkedList actors = new ActorLinkedList();
        for (int i = 0; i < 5; i++) {
            actors.add(new Actor("kms" + i));
        }
        for (int i = 0; i < actors.size(); i++) {
            System.out.println(actors.get(i));
        }
        System.out.println(actors.get(0).getName());

    }
}
