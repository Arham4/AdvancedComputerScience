package org.friscoisd.k12.linkedlist.imdb.part2.test;

import org.friscoisd.k12.linkedlist.imdb.part2.Actor;

/**
 * Created by 142817 on 9/18/2018.
 */
public class NodeTester {

    private Actor currentActor;

    public void run() {
        Actor connor = new Actor("connor");
        Actor tristan = new Actor("tristan");
        Actor amish = new Actor("amish");
        Actor jacque = new Actor("jacque");
        Actor nathan = new Actor("nathan");


        currentActor = nathan;
        nathan.setNext(jacque);
        jacque.setNext(amish);
        amish.setNext(tristan);
        tristan.setNext(connor);

        while (currentActor != null) {
            System.out.println(currentActor.toString());
            currentActor = currentActor.getNext();
        }
    }

    public static void main(String[] args) {
        NodeTester nodeTester = new NodeTester();
        nodeTester.run();
    }
}
