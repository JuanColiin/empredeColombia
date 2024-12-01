package com.ecosistemadigital.emprendeco.repository;

import com.ecosistemadigital.emprendeco.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {
}
