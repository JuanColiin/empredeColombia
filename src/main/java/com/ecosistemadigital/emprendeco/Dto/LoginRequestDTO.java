package com.ecosistemadigital.emprendeco.Dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}