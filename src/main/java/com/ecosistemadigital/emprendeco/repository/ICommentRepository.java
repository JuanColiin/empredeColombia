package com.ecosistemadigital.emprendeco.repository;

import com.ecosistemadigital.emprendeco.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.project p WHERE p.id = :projectId")
    List<Comment> findByProjectId(Long projectId);
}

