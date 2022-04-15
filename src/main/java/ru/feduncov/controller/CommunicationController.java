package ru.feduncov.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feduncov.controller.dto.AuthRequestDTO;
import ru.feduncov.entity.User;
import ru.feduncov.service.CommunicationService;

import java.util.List;

@RestController
@RequestMapping("/api/communication")
public class CommunicationController {

    private final CommunicationService communicationService;

    public CommunicationController(CommunicationService communicationService) {
        this.communicationService = communicationService;
    }

    @PostMapping
    public List<User> getAllUsers(@RequestBody AuthRequestDTO requestDTO) {
        return communicationService.getAllUsers(requestDTO.getEmail(), requestDTO.getPassword());
    }


}
