package ru.feduncov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.feduncov.entity.User;
import ru.feduncov.exceptions.ValidUserException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(CommunicationService.class)
class CommunicationServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommunicationService communicationService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void getAllUsers() {
        when(restTemplate.exchange("http://localhost:8080/api/users", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>() {
                })).thenReturn(getTestUsers());

        assertNotNull(communicationService.getAllUsers("email", "password"));
        assertEquals(3, communicationService.getAllUsers("email", "password").size());
    }

    @Test
    void getAllUsersWithException() {
        when(restTemplate.exchange("http://localhost:8080/api/users", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>() {
                })).thenThrow(HttpClientErrorException.class);

        ValidUserException thrown = assertThrows(ValidUserException.class,
                () -> communicationService.getAllUsers("email", "password"));

        assertTrue(thrown.getMessage().contains("Доступ запрещен"));

    }

    private ResponseEntity<List<User>> getTestUsers() {
        return ResponseEntity.ok(Arrays.asList(
                new User(1L, "alex", "alex.mail.ru", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "ADMIN"),
                new User(2L, "kate", "kate.mail.ru", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "USER"),
                new User(3L, "vlad", "vlad.mail.ru", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "USER")
        ));
    }
}