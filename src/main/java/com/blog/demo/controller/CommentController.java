package com.blog.demo.controller;

import com.blog.demo.dto.request.CreateCommentDTO;
import com.blog.demo.dto.request.UpdateContentCommentDTO;
import com.blog.demo.dto.response.CommentResponseDTO;
import com.blog.demo.dto.response.PageCommentResponseDTO;
import com.blog.demo.security.UsuarioDetailsImpl;
import com.blog.demo.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(
        name="Comentários",
        description = "Rotas de comentários"
)
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            operationId = "createComment"
    )
    public ResponseEntity<CommentResponseDTO> create(
            @RequestBody CreateCommentDTO dto,
            @AuthenticationPrincipal UsuarioDetailsImpl usuarioDetails
    ) {
        CommentResponseDTO created = commentService.create(usuarioDetails.getId(), dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "listAllComments"
    )
    public ResponseEntity<PageCommentResponseDTO> getAll(
            @PageableDefault( size = 10) Pageable pageable
    ) {
        PageCommentResponseDTO pageComment = commentService.getAll(pageable);

        return ResponseEntity.ok()
                .body(pageComment);
    }

    @GetMapping("/post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "listAllCommentsOfPost"
    )
    public ResponseEntity<PageCommentResponseDTO> getAllOfPost(
            @PageableDefault( size = 10) Pageable pageable,
            @PathVariable UUID postId
    ) {
        PageCommentResponseDTO pageComment = commentService.getAllCommentByPostId(pageable, postId);

        return ResponseEntity.ok()
                .body(pageComment);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            description = "Rota para administrador",
            operationId = "listAllCommentsOfUser"
    )
    public ResponseEntity<PageCommentResponseDTO> getAllOfUser(
            @PageableDefault( size = 10) Pageable pageable,
            @PathVariable UUID userId
    ) {
        PageCommentResponseDTO pageComment = commentService.getAllCommentByAuthorId(pageable, userId);

        return ResponseEntity.ok()
                .body(pageComment);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "listAllCommentsOfUserAuthenticated"
    )
    public ResponseEntity<PageCommentResponseDTO> getAllOfUserAdmin(
            @PageableDefault( size = 10) Pageable pageable,
            @AuthenticationPrincipal UsuarioDetailsImpl usuarioDetails
    ) {
        PageCommentResponseDTO pageComment = commentService.getAllCommentByAuthorId(pageable, usuarioDetails.getId());

        return ResponseEntity.ok()
                .body(pageComment);
    }

    @PatchMapping("/{commentId}/content")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "updatedCommentContent"
    )
    public ResponseEntity<CommentResponseDTO> updateContent(
            @RequestBody UpdateContentCommentDTO dto,
            @PathVariable UUID commentId
    ) {
        CommentResponseDTO updated = commentService.updateCommentContent(commentId, dto);

        return ResponseEntity.ok()
                .body(updated);
    }

    @DeleteMapping("{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = "deleteComment"
    )
    public ResponseEntity<Void> delete(
            @PathVariable UUID commentId
    ) {
        commentService.deleteCommentById(commentId);

        return ResponseEntity.noContent().build();
    }

}
