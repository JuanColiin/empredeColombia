package com.ecosistemadigital.emprendeco.Dto;

import com.ecosistemadigital.emprendeco.entity.Role;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Role role; // Enum con valores PUSHING y BUSINESSMAN
}
