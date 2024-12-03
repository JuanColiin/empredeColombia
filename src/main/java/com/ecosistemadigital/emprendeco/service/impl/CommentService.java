package com.ecosistemadigital.emprendeco.service.impl;

import com.ecosistemadigital.emprendeco.Dto.CommentDTO;
import com.ecosistemadigital.emprendeco.entity.Comment;
import com.ecosistemadigital.emprendeco.entity.Project;
import com.ecosistemadigital.emprendeco.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ecosistemadigital.emprendeco.repository.ICommentRepository;
import com.ecosistemadigital.emprendeco.service.ICommentService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {


    private final ICommentRepository commentRepository;

    @Override
    @Transactional
    public CommentDTO save(CommentDTO comment) {
        var commentToSave = Comment.builder()
                        .text(comment.getText())
                        .author(User.builder()
                                .id(comment.getAuthorId())
                                .build())
                        .project(Project.builder()
                                .id(comment.getProjectId())
                                .build())
                        .build();

        commentRepository.save(commentToSave);

        return comment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public CommentDTO update(Long commentId, CommentDTO comment) throws Exception {
        log.info("Actualizando comentario con id: " + commentId);
        Optional<Comment> existingComment = commentRepository.findById(commentId);
        if (existingComment.isEmpty()) {
            throw new Exception("Comentario no encontrado para actualizar");
        }

        Comment updatedComment = existingComment.get().toBuilder()
                .text(comment.getText())
                .build();

        commentRepository.save(updatedComment);

        return CommentDTO.builder()
                .id(updatedComment.getId())
                .text(updatedComment.getText())
                .authorId(updatedComment.getAuthor().getId())
                .authorName(updatedComment.getAuthor().getName())
                .projectId(updatedComment.getProject().getId())
                .build();
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
    public List<CommentDTO> findByProjectId(Long projectId) {
        return commentRepository.findByProjectId(projectId).stream()
                .map(comment -> CommentDTO.builder()
                        .id(comment.getId())
                        .text(comment.getText())
                        .authorId(comment.getAuthor().getId())
                        .authorName(comment.getAuthor().getName())
                        .projectId(comment.getProject().getId())
                        .build())
                .toList();
    }
}
