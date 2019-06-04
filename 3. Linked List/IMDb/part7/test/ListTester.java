package org.friscoisd.k12.linkedlist.imdb.part7.test;

import org.friscoisd.k12.linkedlist.imdb.part7.LinkedList;
import org.friscoisd.k12.linkedlist.imdb.part7.imdb.Actor;
import org.friscoisd.k12.linkedlist.imdb.part7.imdb.Director;
import org.friscoisd.k12.linkedlist.imdb.part7.imdb.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 142817 on 9/24/2018.
 */

public class ListTester {
    public static void main(String[] args) {
        LinkedList movies = new LinkedList();

        try (Scanner scanner = new Scanner(new File("movies.txt"))) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                int date = Integer.parseInt(data.substring(0, 5).trim());
                String title = data.substring(5, 38).trim();
                String[] actorNames = data.substring(38, 84).trim().split(", ");
                String[] directorNames = data.substring(84, data.length()).trim().replace("Dir: ", "").split(", ");

                LinkedList actors = new LinkedList();
                LinkedList directors = new LinkedList();

                Arrays.stream(actorNames).forEach(actorName -> actors.add(new Actor(actorName)));
                Arrays.stream(directorNames).forEach(directorName -> directors.add(new Director(directorName)));

                Movie movie = new Movie(date, title, actors, directors);

                movies.add(movie);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
        System.out.println(((Movie) movies.get(0)).getTitle());

    }
}
