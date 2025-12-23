package com.autohaus.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.autohaus.demo.dto.UserRegisterDTO;
import com.autohaus.demo.dto.UserResponseDTO;
import com.autohaus.demo.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auto-haus/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO register(@Valid @RequestBody UserRegisterDTO dto) {
        return userService.register(dto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUser(@PathVariable Long id) {
        return userService.getById(id);
    }
    @GetMapping("/by-email/{email}")
    public UserResponseDTO getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
