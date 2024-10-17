package org.mathias.movieapiclient.menus;

import org.mathias.movieapiclient.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


import static org.mathias.movieapiclient.utils.InputHandler.*;
@Component
public class MainMenu {

    private final MenuService menuService;

    @Autowired
    public MainMenu(@Lazy MenuService menuService) {
        this.menuService = menuService;
    }
    public void showMenu() {
        while (true) {

            System.out.println("1. Movies");
            System.out.println("2. Directors");
            System.out.println("3. Exit");

            switch (getInputAsInt("Choose an option: ")) {
                case 1:
                    menuService.showMovieMenu();
                    break;
                case 2:
                    menuService.showDirectorMenu();
                    break;
                case 3:
                    showExitMessage();
                    break;
                default:
                    System.out.println("Invalid option");
                    showMenu();
            }
        }
    }



    public void showDirectorsMenu() {
        System.out.println("1. Add director");
        System.out.println("2. List directors");
        System.out.println("3. Back");

        switch (getInputAsInt("Choose an option: ")) {
            case 1:
                System.out.println("Add director");
                break;
            case 2:
                System.out.println("List directors");
                break;
            case 3:
                showMenu();
                break;
            default:
                System.out.println("Invalid option");
                showDirectorsMenu();
        }
    }

    public void showExitMessage() {
        System.out.println("Goodbye!");
    }



}
