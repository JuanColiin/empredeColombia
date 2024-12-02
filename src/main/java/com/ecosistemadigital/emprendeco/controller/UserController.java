package com.ecosistemadigital.emprendeco.controller;

import com.ecosistemadigital.emprendeco.Dto.LoginResponseDTO;
import com.ecosistemadigital.emprendeco.Dto.UserResponseDTO;
import com.ecosistemadigital.emprendeco.entity.User;
import com.ecosistemadigital.emprendeco.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        try {
            User userFind = userService.authenticate(user);

            // Convertir User en LoginResponseDTO
            LoginResponseDTO responseDTO = LoginResponseDTO.builder()
                    .id(userFind.getId())
                    .name(userFind.getName())
                    .email(userFind.getEmail())
                    .role(userFind.getRole().toString())
                    .build();

            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Name, email, and password are required");
        }

        try {
            User registeredUser = userService.registerUser(user);

            // Convertir la entidad User en UserResponseDTO
            UserResponseDTO responseDTO = UserResponseDTO.builder()
                    .id(registeredUser.getId())
                    .name(registeredUser.getName())
                    .email(registeredUser.getEmail())
                    .role(registeredUser.getRole().toString())
                    .build();

            return ResponseEntity.ok(responseDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
