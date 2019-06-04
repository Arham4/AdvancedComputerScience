package org.friscoisd.k12.linkedlist.imdb.part7.test;

import org.friscoisd.k12.linkedlist.imdb.part7.Node;
import org.friscoisd.k12.linkedlist.imdb.part7.imdb.Actor;

/**
 * Created by 142817 on 9/18/2018.
 */
public class NodeTester {

    private Node currentNode;

    public void run() {
        Node connor = new Node(new Actor("connor"));
        Node tristan = new Node(new Actor("tristan"));
        Node amish = new Node(new Actor("amish"));
        Node jacque = new Node(new Actor("jacque"));
        Node nathan = new Node(new Actor("nathan"));


        currentNode = nathan;
        nathan.setNext(jacque);
        jacque.setNext(amish);
        amish.setNext(tristan);
        tristan.setNext(connor);

        while (currentNode != null) {
            System.out.println(currentNode.toString());
            currentNode = currentNode.getNext();
        }

        System.out.println(((Actor) nathan.get()).getName());
    }

    public static void main(String[] args) {
        NodeTester nodeTester = new NodeTester();
        nodeTester.run();
    }
}
