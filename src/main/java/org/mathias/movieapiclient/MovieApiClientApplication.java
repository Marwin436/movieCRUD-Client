package org.mathias.movieapiclient;

import org.mathias.movieapiclient.menus.MainMenu;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieApiClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieApiClientApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(MainMenu mainMenu) {
        return args -> {
            mainMenu.showMenu();
        };
    }
}
