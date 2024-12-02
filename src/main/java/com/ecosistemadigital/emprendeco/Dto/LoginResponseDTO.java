package com.ecosistemadigital.emprendeco.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String role; // Enum convertido a String
}
