package com.ecosistemadigital.emprendeco.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_description")
    private String description;

    @Column(name = "project_category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "project_city")
    private String city;

    @Column(name = "project_picture")
    private String picture;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "project-comments")
    private List<Comment> comments;


    @ManyToOne(fetch = FetchType.LAZY)  // Aseguramos que la relación con User se cargue perezosamente
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference(value = "user-projects") // Evitar ciclos de referencia y datos innecesarios
    private User pushing;
}
