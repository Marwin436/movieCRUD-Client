package org.mathias.movieapiclient.menus;

import org.mathias.movieapiclient.clients.DirectorClient;
import org.mathias.movieapiclient.dtos.DirectorDTO;
import org.mathias.movieapiclient.dtos.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.mathias.movieapiclient.utils.InputHandler.*;
@Component
public class DirectorMenu {

    DirectorClient directorClient = new DirectorClient();
    public void showMenu() {
        System.out.println("1. List all directors");
        System.out.println("2. List movies by director");
        System.out.println("3. Find director by id");
        System.out.println("4. Add director");
        System.out.println("5. Update director");
        System.out.println("6. Delete director");
        System.out.println("7. Back to main menu");

        switch (getInputAsInt("Choose an option: ")) {
            case 1:
                listDirectors();
                break;
            case 2:
                listMoviesByDirector();
                break;
            case 3:
                listDirectorById();
                break;
            case 4:
                addDirector();
                break;
            case 5:
                updateDirector();
                break;
            case 6:
                deleteDirector();
                break;
            case 7:
                break;
            default:
                System.out.println("Invalid option");
                showMenu();
        }
    }

    private void deleteDirector() {
        Long id = getInputAsLong("Enter director id to delete:");
        directorClient.deleteDirector(id);
    }

    private void updateDirector() {
        Long id = getInputAsLong("Enter director id to update:");
        DirectorDTO director = directorClient.getDirectorById(id).get();
        director.setName(getInputAsString("Enter name: "));
        director.setBirthYear(getInputAsInt("Enter birth year: "));
        directorClient.updateDirector(id, director);
    }

    private void addDirector() {
        DirectorDTO newDirector = new DirectorDTO();
        newDirector.setName(getInputAsString("Enter name: "));
        newDirector.setBirthYear(getInputAsInt("Enter birth year: "));
        directorClient.addDirector(newDirector);
    }

    private void listDirectorById() {
        Long id = getInputAsLong("Enter director id to find:");
        if (directorClient.getDirectorById(id).isPresent()) {
            directorClient.getDirectorById(id).get().printDirector();
        } else {
            System.out.println("Director not found");
        }
    }

    private void listMoviesByDirector() {
        String name = getInputAsString("Enter the name of the director to list movies for:");

        try {
            // Hämta regissören
            Optional<DirectorDTO> director = directorClient.getDirectorByName(name);

            // Om regissören hittades
            if (director.isPresent()) {
                director.get().getMovies().forEach(MovieDTO::printMovie);
            } else {
                System.out.println("Director not found");
            }

        } catch (RuntimeException e) {
            // Hantera andra fel (t.ex. nätverksproblem, serverfel)
            System.out.println("An error occurred while fetching the director: " + e.getMessage());
        }
    }


    private void listDirectors() {
        directorClient.getAllDirectors().forEach(DirectorDTO::printDirector);
    }
}
