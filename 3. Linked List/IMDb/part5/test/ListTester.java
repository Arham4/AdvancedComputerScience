package org.friscoisd.k12.linkedlist.imdb.part5.test;

import org.friscoisd.k12.linkedlist.imdb.part5.LinkedList;
import org.friscoisd.k12.linkedlist.imdb.part5.actor.Actor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 142817 on 9/24/2018.
 */

public class ListTester {
    public static void main(String[] args) {
        LinkedList actors = new LinkedList();
        try (Scanner scanner = new Scanner(new File("actors.txt"))) {
            while (scanner.hasNextLine()) {
                actors.add(new Actor(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < actors.size(); i++) {
            System.out.println(actors.get(i));
        }
        System.out.println(((Actor) actors.get(0)).getName());

    }
}
