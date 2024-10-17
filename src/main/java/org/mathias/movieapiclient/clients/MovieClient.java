package org.mathias.movieapiclient.clients;

import org.mathias.movieapiclient.dtos.MovieDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MovieClient {

    private final String BASE_URL = "http://movie-api-env.eba-e2siqrjw.eu-north-1.elasticbeanstalk.com/movies";  // Base URL for the API
    private final RestTemplate restTemplate;

    public MovieClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<MovieDTO> getAllMovies() {
        ResponseEntity<MovieDTO[]> response = restTemplate.getForEntity(BASE_URL, MovieDTO[].class);
        MovieDTO[] movies = response.getBody();
        return Arrays.asList(movies);
    }

    public Optional<MovieDTO> getMovieById(Long id) {
        String url = BASE_URL + "/" + id;
        ResponseEntity<MovieDTO> response = restTemplate.getForEntity(url, MovieDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<MovieDTO> getMovieByTitle(String title) {
        String url = BASE_URL + "/title/" + title;
        ResponseEntity<MovieDTO> response = restTemplate.getForEntity(url, MovieDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public List<MovieDTO> getMoviesByGenre(String genre) {
        String url = BASE_URL + "/genre/" + genre;
        ResponseEntity<MovieDTO[]> response = restTemplate.getForEntity(url, MovieDTO[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            return List.of();  // Return an empty list if no movies are found
        }
    }

    public List<MovieDTO> getMoviesByYear(int year) {
        String url = BASE_URL + "/year/" + year;
        ResponseEntity<MovieDTO[]> response = restTemplate.getForEntity(url, MovieDTO[].class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Arrays.asList(response.getBody());
        } else {
            return List.of();
        }
    }

    public MovieDTO addMovie(MovieDTO movie) {
        ResponseEntity<MovieDTO> response = restTemplate.postForEntity(BASE_URL + "/add", movie, MovieDTO.class);
        return response.getBody();
    }

    public Optional<MovieDTO> updateMovie(Long id, MovieDTO movie) {
        String url = BASE_URL + "/update/" + id;
        HttpEntity<MovieDTO> request = new HttpEntity<>(movie);
        ResponseEntity<MovieDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, MovieDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public void deleteMovie(Long id) {
        String url = BASE_URL + "/delete/" + id;
        restTemplate.delete(url);
    }
}
