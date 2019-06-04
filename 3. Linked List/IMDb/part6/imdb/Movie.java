package org.friscoisd.k12.linkedlist.imdb.part6.imdb;

import org.friscoisd.k12.linkedlist.imdb.part6.LinkedList;

/**
 * Created by 142817 on 9/24/2018.
 */
public class Movie {
    private int date;
    private String title;
    private LinkedList actors;
    private LinkedList directors;

    public Movie() {
        date = 0;
        title = null;
        actors = new LinkedList();
        directors = new LinkedList();
    }

    public Movie(int date, String title, LinkedList actors, LinkedList directors) {
        this.date = date;
        this.title = title;
        this.actors = actors;
        this.directors = directors;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LinkedList getActors() {
        return actors;
    }

    public void setActors(LinkedList actors) {
        this.actors = actors;
    }

    public LinkedList getDirectors() {
        return directors;
    }

    public void setDirectors(LinkedList directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "Movie: " + title + "\n" +
                "Date: " + date + "\n" +
                "Actors: " + actors.toString() + "\n" +
                "Directors: " + directors.toString() + "\n";
    }
}
