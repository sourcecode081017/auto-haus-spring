package com.autohaus.demo.service;

import org.springframework.stereotype.Service;

import com.autohaus.demo.dto.UserRegisterDTO;
import com.autohaus.demo.dto.UserResponseDTO;
import com.autohaus.demo.entity.User;
import com.autohaus.demo.exception.EmailAlreadyExistsException;
import com.autohaus.demo.exception.UserNotFoundException;
import com.autohaus.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserResponseDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    UserResponseDTO dto = new UserResponseDTO();
                    dto.setId(user.getId());
                    dto.setFirstName(user.getFirstName());
                    dto.setLastName(user.getLastName());
                    dto.setEmail(user.getEmail());
                    dto.setDateOfBirth(user.getDateOfBirth());
                    return dto;
                })
                .orElse(null);
    }

    public UserResponseDTO register(UserRegisterDTO dto) {

        userRepository.findByEmail(dto.email())
                .ifPresent(user -> {
                    throw new EmailAlreadyExistsException("Email already exists");
                });

        User user = User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .dateOfBirth(dto.dateOfBirth())
                .build();

        User saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getFirstName(),
                saved.getLastName(),
                saved.getEmail(),
                saved.getDateOfBirth()
        );
    }

    public UserResponseDTO getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDateOfBirth()
        );
    }
}
