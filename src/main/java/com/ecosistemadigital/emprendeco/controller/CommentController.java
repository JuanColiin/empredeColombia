package com.ecosistemadigital.emprendeco.controller;

import com.ecosistemadigital.emprendeco.Dto.CommentDTO;
import com.ecosistemadigital.emprendeco.entity.Comment;
import com.ecosistemadigital.emprendeco.entity.Project;
import com.ecosistemadigital.emprendeco.entity.User;
import com.ecosistemadigital.emprendeco.service.IProjectService;
import com.ecosistemadigital.emprendeco.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.ecosistemadigital.emprendeco.service.ICommentService;

import java.util.List;
import java.util.Optional;


    @RestController
    @RequestMapping("comment")
    @RequiredArgsConstructor
    @Slf4j
    public class CommentController {

        private final ICommentService commentService;
        private final IProjectService projectService;
        private final UserService userService;

        // Obtener todos los comentarios
        @GetMapping
        public ResponseEntity<List<Comment>> getAllComments() {
            List<Comment> comments = commentService.findAll();
            return ResponseEntity.ok(comments);
        }

        // Obtener un comentario por ID
        @GetMapping("/{id}")
        public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
            Optional<Comment> comment = commentService.findById(id);
            return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        // Obtener comentarios de un proyecto específico
        @GetMapping("/project/{projectId}")
        public ResponseEntity<List<CommentDTO>> getCommentsByProjectId(@PathVariable Long projectId) {
            return ResponseEntity.ok(commentService.findByProjectId(projectId));
        }

        // Crear un nuevo comentario
        @PostMapping
        public ResponseEntity<?> createComment(@RequestBody CommentDTO comment) {
            // Verificar si el proyecto existe
            try {
                var savedComment = commentService.save(comment);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
            } catch (Exception e) {
                log.error("Error al crear comentario", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }




        @PutMapping("/{id}")
        public ResponseEntity<CommentDTO> updateComment(@PathVariable Long id, @RequestBody CommentDTO comment) throws Exception {
            CommentDTO updatedComment = commentService.update(id, comment);
            return ResponseEntity.ok(updatedComment);
        }


        // Eliminar un comentario
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteComment(@PathVariable Long id) throws Exception {
            commentService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

