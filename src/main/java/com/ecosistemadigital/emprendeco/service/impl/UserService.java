package com.ecosistemadigital.emprendeco.service.impl;

import com.ecosistemadigital.emprendeco.entity.Role;
import com.ecosistemadigital.emprendeco.entity.User;
import com.ecosistemadigital.emprendeco.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository userRepository;

    public User authenticate(User user) {
        return userRepository.findByEmail(user.getEmail())
                .filter(user1 -> user1.getPassword().equals(user.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está en uso");
        }

        User newUser = User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        return userRepository.save(newUser);
    }

}