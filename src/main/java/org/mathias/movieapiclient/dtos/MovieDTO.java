package org.mathias.movieapiclient.dtos;

import lombok.Data;

@Data
public class MovieDTO {
    private long id;
    private String title;
    private String genre;
    private int year;
    private DirectorDTO director;

    public void printMovie() {
        System.out.println("**************************************");
        System.out.println("Title: " + title + " (" + year + ")");
        System.out.println("Genre: " + genre);
        if (director != null) {
            System.out.print("Director: ");director.printDirector();
        }
        System.out.println("**************************************");
        System.out.println();
    }
}