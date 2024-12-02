package com.ecosistemadigital.emprendeco.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role; // Representado como String para simplificar
}
