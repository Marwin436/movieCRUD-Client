package org.mathias.movieapiclient.services;

import org.mathias.movieapiclient.menus.DirectorMenu;
import org.mathias.movieapiclient.menus.MainMenu;
import org.mathias.movieapiclient.menus.MovieMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MainMenu mainMenu;
    private final MovieMenu movieMenu;
    private final DirectorMenu directorMenu;

    @Autowired
    public MenuService(MainMenu mainMenu, MovieMenu movieMenu, DirectorMenu directorMenu) {
        this.mainMenu = mainMenu;
        this.movieMenu = movieMenu;
        this.directorMenu = directorMenu;
    }

    public void showMainMenu() {
        mainMenu.showMenu();
    }

    public void showMovieMenu() {
        movieMenu.showMenu();
    }

    public void showDirectorMenu() {
        directorMenu.showMenu();
    }
}
