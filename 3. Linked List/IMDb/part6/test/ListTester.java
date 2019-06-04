package org.friscoisd.k12.linkedlist.imdb.part6.test;

import org.friscoisd.k12.linkedlist.imdb.part6.LinkedList;
import org.friscoisd.k12.linkedlist.imdb.part6.imdb.Actor;
import org.friscoisd.k12.linkedlist.imdb.part6.imdb.Director;
import org.friscoisd.k12.linkedlist.imdb.part6.imdb.Movie;

/**
 * Created by 142817 on 9/24/2018.
 */

public class ListTester {
    public static void main(String[] args) {
        LinkedList movies = new LinkedList();
        for (int i = 0; i < 5; i++) {
            LinkedList actors = new LinkedList();
            LinkedList directors = new LinkedList();

            actors.add(new Actor("connor"));
            actors.add(new Actor("Arham"));
            directors.add(new Director("some big shot lol"));

            Movie movie = new Movie(30, "To Kill Connor", actors, directors);
            movies.add(movie);
        }
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
        System.out.println(((Movie) movies.get(0)).getTitle());

    }
}
