package com.ecosistemadigital.emprendeco.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private String text;
    private String authorName;  // Extraer solo el nombre del autor
    private Long projectId;

}
