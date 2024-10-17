package org.mathias.movieapiclient.clients;

import org.mathias.movieapiclient.dtos.DirectorDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DirectorClient {

    private final String BASE_URL = "http://movie-api-env.eba-e2siqrjw.eu-north-1.elasticbeanstalk.com/directors";  // Base URL for the API
    private final RestTemplate restTemplate;

    public DirectorClient() {
        this.restTemplate = new RestTemplate();
    }

    public List<DirectorDTO> getAllDirectors() {
        ResponseEntity<DirectorDTO[]> response = restTemplate.getForEntity(BASE_URL, DirectorDTO[].class);
        DirectorDTO[] directors = response.getBody();
        return Arrays.asList(directors); // Returnera som en lista
    }

    public Optional<DirectorDTO> getDirectorById(Long id) {
        String url = BASE_URL + "/id/" + id;
        ResponseEntity<DirectorDTO> response = restTemplate.getForEntity(url, DirectorDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public Optional<DirectorDTO> getDirectorByName(String name) {
        try {
            // Försök hämta regissören från API:et
            return Optional.ofNullable(restTemplate.getForEntity( BASE_URL + "/" + name, DirectorDTO.class).getBody());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Hantera 404 (regissören hittades inte)
                return Optional.empty(); // Returnera tom Optional vid 404
            } else {
                // Hantera andra typer av fel, t.ex. serverfel
                System.out.println("An error occurred: " + e.getStatusCode());
                throw new RuntimeException("Failed to fetch director: " + e.getMessage()); // Eller ett anpassat undantag
            }
        }
    }


    public DirectorDTO addDirector(DirectorDTO directorDTO) {
        ResponseEntity<DirectorDTO> response = restTemplate.postForEntity(BASE_URL + "/add", directorDTO, DirectorDTO.class);
        return response.getBody();
    }

    public Optional<DirectorDTO> updateDirector(Long id, DirectorDTO directorDTO) {
        String url = BASE_URL + "/update/" + id;
        HttpEntity<DirectorDTO> request = new HttpEntity<>(directorDTO);
        ResponseEntity<DirectorDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, DirectorDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }

    public void deleteDirector(Long id) {
        String url = BASE_URL + "/delete/" + id;
        restTemplate.delete(url);
    }
}
