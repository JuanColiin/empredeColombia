package com.ecosistemadigital.emprendeco.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "user_password", nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-comments")// Marca esta propiedad como gestionada para la serialización
    private List<Comment> comments;

    @OneToMany(mappedBy = "pushing", fetch = FetchType.LAZY)
    @JsonManagedReference(value = "user-projects") // Añadir una referencia personalizada
    private List<Project> projects;

    public User orElseThrow(Object userNotFound) {
        return null;
    }
}

