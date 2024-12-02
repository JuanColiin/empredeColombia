package com.ecosistemadigital.emprendeco.service;

import com.ecosistemadigital.emprendeco.Dto.ProjectDTO;
import com.ecosistemadigital.emprendeco.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {
    Project save(Project project); // Guardar un proyecto (nuevo o actualizado)
    Optional<ProjectDTO> findById(Long id); // Buscar un proyecto por ID
    Project update(Project project); // Actualizar un proyecto y devolver el actualizado
    void delete(Long id) throws Exception; // Eliminar un proyecto
    List<ProjectDTO> findAll(); // Buscar todos los proyectos
}
