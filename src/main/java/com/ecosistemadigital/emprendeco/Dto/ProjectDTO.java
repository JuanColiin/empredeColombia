package com.ecosistemadigital.emprendeco.Dto;

import com.ecosistemadigital.emprendeco.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private Category category;
    private String city;
    private String picture;
    private String pushingName;
    private String pushingEmail;
    private List<CommentDTO> comments;
}
