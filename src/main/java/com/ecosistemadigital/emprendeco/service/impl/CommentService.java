package com.ecosistemadigital.emprendeco.service.impl;

import com.ecosistemadigital.emprendeco.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ecosistemadigital.emprendeco.repository.ICommentRepository;
import com.ecosistemadigital.emprendeco.service.ICommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final ICommentRepository commentRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public void update(Comment comment) throws Exception {
        if (!commentRepository.existsById(comment.getId())) {
            throw new Exception("Comentario no encontrado para actualizar");
        }
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Optional<Comment> commentToLookFor = findById(id);
        if (commentToLookFor.isPresent()) {
            commentRepository.deleteById(id);
        } else {
            throw new Exception("No se pudo eliminar el comentario con id: " + id);
        }
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }


    @Override
    public List<Comment> findByProjectId(Long projectId) {
        return commentRepository.findByProjectId(projectId);
    }
}
