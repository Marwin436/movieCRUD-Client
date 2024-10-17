package org.mathias.movieapiclient.menus;

import org.mathias.movieapiclient.clients.MovieClient;
import org.mathias.movieapiclient.dtos.DirectorDTO;
import org.mathias.movieapiclient.dtos.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mathias.movieapiclient.utils.InputHandler.*;
@Component
public class MovieMenu {

    MovieClient movieClient = new MovieClient();

    public void showMenu() {
        System.out.println("1. Add movie");
        System.out.println("2. List movies");
        System.out.println("3. Update movie");
        System.out.println("4. Delete movie");
        System.out.println("5. Back");

        switch (getInputAsInt("Choose an option: ")) {
            case 1:
                addMovie();
                break;
            case 2:
                listMovies();
                break;
            case 3:
                updateMovie();
                break;
            case 4:
                deleteMovie();
                break;
            case 5:
                break;
        }
    }

    public void addMovie() {
        MovieDTO movie = new MovieDTO();
        movie.setTitle(getInputAsString("Enter title: "));
        movie.setGenre(getInputAsString("Enter genre: "));
        movie.setYear(getInputAsInt("Enter year: "));

        if (askToAddDirector()) {
            DirectorDTO director = new DirectorDTO();
            director.setName(getInputAsString("Enter name for director: "));
            director.setBirthYear(getInputAsInt("Enter birth year for director: "));
            movie.setDirector(director);
        }

        movieClient.addMovie(movie);
    }

    private boolean askToAddDirector() {
        String input;
        while (true) {
            input = getInputAsString("Do you want to add a director? (y/n)").toLowerCase();
            switch (input) {
                case "y":
                    return true;
                case "n":
                    return false;
                default:
                    System.out.println("Invalid option, please enter 'y' or 'n'.");
            }
        }
    }


    private void deleteMovie() {
        System.out.println("Enter movie ID to delete: ");
        Long id = getInputAsLong("Enter movie ID to delete: ");
        try {
            movieClient.deleteMovie(id);
        } catch (Exception e) {
            System.out.println("Movie not found");

        }

    }

    private void updateMovie() {
        Long id = getInputAsLong("Enter movie ID to update: ");
        MovieDTO movie = movieClient.getMovieById(id).orElseThrow();
        movie.setTitle(getInputAsString("Enter new title: "));
        movie.setGenre(getInputAsString("Enter new genre: "));
        movie.setYear(getInputAsInt("Enter new year: "));
        switch (getInputAsString("Do you want to change the director? (y/n)")) {
            case "y":
                DirectorDTO director = new DirectorDTO();
                director.setName(getInputAsString("Enter new name for director: "));
                director.setBirthYear(getInputAsInt("Enter new birth year for director: "));
                movie.setDirector(director);
                break;
            case "n":
                break;
            default:
                System.out.println("Invalid option");
                updateMovie();
        }

        movieClient.updateMovie(id, movie);
    }

    private void listMovies() {
        List<MovieDTO> movies = movieClient.getAllMovies();
        movies.forEach(MovieDTO::printMovie);
    }
}
