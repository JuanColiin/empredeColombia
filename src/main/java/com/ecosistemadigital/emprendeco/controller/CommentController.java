package com.ecosistemadigital.emprendeco.controller;

import com.ecosistemadigital.emprendeco.entity.Comment;
import com.ecosistemadigital.emprendeco.entity.Project;
import com.ecosistemadigital.emprendeco.service.IProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.ecosistemadigital.emprendeco.service.ICommentService;

import java.util.List;
import java.util.Optional;


    @RestController
    @RequestMapping("comment")
    @RequiredArgsConstructor
    public class CommentController {

        private final ICommentService commentService;
        private final IProjectService projectService;

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
        public ResponseEntity<List<Comment>> getCommentsByProjectId(@PathVariable Long projectId) {
            List<Comment> comments = commentService.findByProjectId(projectId);
            return ResponseEntity.ok(comments);
        }

        // Crear un nuevo comentario
        @PostMapping
        public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
            // Verificar si el proyecto existe
            if (comment.getProject() == null || comment.getProject().getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Error si no se pasa un proyecto
            }

            Optional<Project> existingProject = projectService.findById(comment.getProject().getId());
            if (existingProject.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Error si el proyecto no existe
            }

            comment.setProject(existingProject.get());
            Comment savedComment = commentService.save(comment);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
        }




        @PutMapping("/{id}")
        public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) throws Exception {
            Optional<Comment> existingComment = commentService.findById(id);
            if (existingComment.isPresent()) {
                // Asegúrate de que el comentario tiene el ID correcto
                comment.setId(id);

                // Verificar si el proyecto existe antes de asociarlo
                if (comment.getProject() != null && comment.getProject().getId() == null) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Si no se pasa un proyecto válido
                }

                // Si todo está bien, actualizamos el comentario
                commentService.update(comment);
                return ResponseEntity.ok(comment);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        // Eliminar un comentario
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteComment(@PathVariable Long id) throws Exception {
            commentService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }

