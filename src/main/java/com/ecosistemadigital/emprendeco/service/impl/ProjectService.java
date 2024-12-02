package com.ecosistemadigital.emprendeco.service.impl;

import com.ecosistemadigital.emprendeco.Dto.CommentDTO;
import com.ecosistemadigital.emprendeco.Dto.ProjectDTO;
import com.ecosistemadigital.emprendeco.entity.Project;
import com.ecosistemadigital.emprendeco.entity.User;
import com.ecosistemadigital.emprendeco.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecosistemadigital.emprendeco.repository.IProjectRepository;
import com.ecosistemadigital.emprendeco.service.IProjectService;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProjectService implements IProjectService {

    private final IProjectRepository projectRepository;
    private final IUserRepository userRepository;

    @Override
    public Project save(Project project) {
        User user = userRepository.findById(project.getPushing().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        project.setPushing(user); // Asociamos el usuario al proyecto
        return projectRepository.save(project);
    }

    @Override
    public Optional<ProjectDTO> findById(Long id) {
        return projectRepository.findById(id).map(project -> ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .category(project.getCategory())
                .city(project.getCity())
                .picture(project.getPicture())
                .pushingName(project.getPushing().getName())
                .pushingEmail(project.getPushing().getEmail())
                .comments(project.getComments().stream()
                        .map(comment -> CommentDTO.builder()
                                .id(comment.getId())
                                .text(comment.getText())
                                .authorId(comment.getAuthor().getId())
                                .authorName(comment.getAuthor().getName())
                                .build())
                        .toList())
                .build());
    }

    @Override
    public Project update(Project project) {
        Optional<Project> existingProject = projectRepository.findById(project.getId());

        if (existingProject.isPresent()) {
            Project updatedProject = existingProject.get();
            updatedProject.setName(project.getName());
            updatedProject.setDescription(project.getDescription());
            updatedProject.setCategory(project.getCategory());
            updatedProject.setCity(project.getCity());
            updatedProject.setComments(project.getComments());
            updatedProject.setPushing(project.getPushing());

            return projectRepository.save(updatedProject);
        } else {
            throw new RuntimeException("Proyecto no encontrado");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Project> projectToLookFor = projectRepository.findById(id);
        if (projectToLookFor.isPresent()) {
            projectRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se pudo eliminar el proyecto con id: " + id);
        }
    }

    @Override
    public List<ProjectDTO> findAll() {
        return projectRepository.findAll().stream()
                .map(project -> ProjectDTO.builder()
                        .id(project.getId())
                        .name(project.getName())
                        .description(project.getDescription())
                        .category(project.getCategory())
                        .city(project.getCity())
                        .picture(project.getPicture())
                        .build())
                .toList();
    }
}
