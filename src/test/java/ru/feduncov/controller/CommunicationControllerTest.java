package ru.feduncov.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.feduncov.controller.dto.AuthRequestDTO;
import ru.feduncov.entity.User;
import ru.feduncov.service.CommunicationService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(CommunicationController.class)
class CommunicationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommunicationService communicationService;

    @Test
    void getAllUsers() throws Exception {
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        authRequestDTO.setEmail("email");
        authRequestDTO.setPassword("password");

        when(communicationService.getAllUsers(authRequestDTO.getEmail(), authRequestDTO.getPassword()))
                .thenReturn(getTestUsers());

        String expected = objectMapper.writeValueAsString(getTestUsers());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/communication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json(expected));

    }

    private List<User> getTestUsers() {
        return Arrays.asList(
                new User(1L, "alex", "alex.mail.ru", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "ADMIN"),
                new User(2L, "kate", "kate.mail.ru", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "USER"),
                new User(3L, "vlad", "vlad.mail.ru", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "USER")
        );
    }
}