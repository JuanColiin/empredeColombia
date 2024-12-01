package com.ecosistemadigital.emprendeco.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;

    @Column(name = "name_project")
    private String name;

    @Column(name = "description_project")
    private String description;

    @Column(name = "category_project")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "city_project")
    private String city;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments;


    @ManyToOne(fetch = FetchType.LAZY)  // Aseguramos que la relación con User se cargue perezosamente
    @JoinColumn(name = "id_user", nullable = false)
    @JsonIgnoreProperties({"projects", "hibernateLazyInitializer", "handler"})  // Evitar ciclos de referencia y datos innecesarios
    private User pushing;
}
