package com.ecosistemadigital.emprendeco.service;

import com.ecosistemadigital.emprendeco.Dto.CommentDTO;
import com.ecosistemadigital.emprendeco.entity.Comment;
import java.util.List;
import java.util.Optional;

public interface ICommentService {
    Comment save(CommentDTO comment);
    Optional<Comment> findById(Long id);
    CommentDTO update(Long commentId,CommentDTO comment) throws Exception;
    void delete(Long id) throws Exception;
    List<Comment> findAll();
    List<CommentDTO> findByProjectId(Long projectId);
}
