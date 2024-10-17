package org.mathias.movieapiclient.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DirectorDTO {
    private long id;
    private String name;
    private int birthYear;
    private List<MovieDTO> movies;

    public void printDirector() {
        System.out.println(name + " (" + birthYear + ")");

    }


}