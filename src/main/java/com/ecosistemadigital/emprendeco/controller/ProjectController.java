package com.ecosistemadigital.emprendeco.controller;

import com.ecosistemadigital.emprendeco.entity.Project;
import com.ecosistemadigital.emprendeco.repository.IProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ecosistemadigital.emprendeco.service.IProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final IProjectService iProjectService;
    private final IProjectRepository projectRepository;

    @PostMapping
    public ResponseEntity<Project> save(@RequestBody Project project) {
        try {
            Project savedProject = iProjectService.save(project);
            return ResponseEntity.ok(savedProject);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable Long id) {
        Optional<Project> project = iProjectService.findById(id);
        return project.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit")
    public ResponseEntity<Project> updateProject(@RequestBody Project updatedProject) {
        // Verifica si el proyecto existe
        Optional<Project> existingProject = projectRepository.findById(updatedProject.getId());
        if (existingProject.isPresent()) {
            Project project = existingProject.get();

            // Actualiza los atributos del proyecto
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            project.setCategory(updatedProject.getCategory());
            project.setCity(updatedProject.getCity());

            // Guarda el proyecto actualizado
            Project savedProject = projectRepository.save(project);
            return ResponseEntity.ok(savedProject);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            iProjectService.delete(id);
            return ResponseEntity.ok("El proyecto se eliminó correctamente");
        } catch (Exception ex) {
            return ResponseEntity.status(400).body("No se pudo eliminar el proyecto");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> findAll() {
        return ResponseEntity.ok(iProjectService.findAll());
    }
}
