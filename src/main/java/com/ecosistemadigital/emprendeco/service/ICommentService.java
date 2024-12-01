package com.ecosistemadigital.emprendeco.service;

import com.ecosistemadigital.emprendeco.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Comment save (Comment comment);
    Optional<Comment> findById(Long id);
    void update(Comment comment) throws Exception;
    void delete(Long id) throws Exception;
    List<Comment> findAll();
    List<Comment> findByProjectId(Long projectId);
}
