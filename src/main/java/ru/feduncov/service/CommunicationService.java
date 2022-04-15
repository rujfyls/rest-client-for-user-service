package ru.feduncov.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.feduncov.entity.User;
import ru.feduncov.exceptions.ValidUserException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunicationService {

    private final RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api/users";

    public CommunicationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers(String email, String password) {

        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(email, password));
        ResponseEntity<List<User>> responseEntity;
        List<User> users = new ArrayList<>();

        try {
            responseEntity = restTemplate.exchange(URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<User>>() {});
            users = responseEntity.getBody();
        } catch (HttpClientErrorException ignored) {
            throw new ValidUserException("Доступ запрещен");
        }

        return users;
    }
}
