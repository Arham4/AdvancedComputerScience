package org.friscoisd.k12.linkedlist.imdb.part8.imdb;

import org.friscoisd.k12.linkedlist.imdb.part7.LinkedList;
import org.friscoisd.k12.linkedlist.imdb.part7.imdb.Actor;
import org.friscoisd.k12.linkedlist.imdb.part7.imdb.Director;
import org.friscoisd.k12.linkedlist.imdb.part7.imdb.Movie;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by 142817 on 9/24/2018.
 */
public class IMDb {
    public static void main(String[] args) {
        LinkedList actors = new LinkedList();
        LinkedList movies = new LinkedList();

        try (Scanner scanner = new Scanner(new File("actors.txt"))) {
            while (scanner.hasNextLine()) {
                actors.add(new Actor(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(new File("movies.txt"))) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                int date = Integer.parseInt(data.substring(0, 5).trim());
                String title = data.substring(5, 38).trim();
                String[] actorNames = data.substring(38, 84).trim().split(", ");
                String[] directorNames = data.substring(84, data.length()).trim().replace("Dir: ", "").split(", ");

                LinkedList moviesActorsLinkedList = new LinkedList();
                LinkedList moviesDirectorsLinkedList = new LinkedList();

                Arrays.stream(actorNames).forEach(actorName -> moviesActorsLinkedList.add(new Actor(actorName)));
                Arrays.stream(directorNames).forEach(directorName -> moviesDirectorsLinkedList.add(new Director(directorName)));

                Movie movie = new Movie(date, title, moviesActorsLinkedList, moviesDirectorsLinkedList);

                movies.add(movie);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int actorI = 0; actorI < actors.size(); actorI++) {
            Actor actor = (Actor) actors.get(actorI);
            System.out.println(actor);
            for (int movieI = 0; movieI < movies.size(); movieI++) {
                Movie movie = (Movie) movies.get(movieI);
                LinkedList movieActors = movie.getActors();
                for (int movieActorI = 0; movieActorI < movieActors.size(); movieActorI++) {
                    if (movieActors.get(movieActorI).toString().equalsIgnoreCase(actor.toString())) {
                        System.out.println("Date: " + movie.getDate() + "\nMovie: " + movie.getTitle());
                    }
                }
            }
            System.out.println("---");
        }
    }
}
