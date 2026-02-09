package com.blog.demo.controller;

import com.blog.demo.dto.request.CreatePostDTO;
import com.blog.demo.dto.request.UpdateContentPostDTO;
import com.blog.demo.dto.response.PagePostResponseDTO;
import com.blog.demo.dto.response.PostResponseDTO;
import com.blog.demo.security.UsuarioDetailsImpl;
import com.blog.demo.service.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/post")
@Tag(
        name = "Posts",
        description = "Rotas para controle e manipulação de posts"
)
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> create(
            @RequestBody @Valid CreatePostDTO dto,
            @AuthenticationPrincipal UsuarioDetailsImpl authenticateUsuario
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED).body(postService.create(dto, authenticateUsuario.getId()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getById(
        @PathVariable UUID postId
    ) {
        return ResponseEntity
                .ok().body(postService.getById(postId));
    }

    @GetMapping
    public ResponseEntity<PagePostResponseDTO> getAll(
             @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity
                .ok().body(postService.getAll(pageable));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<PagePostResponseDTO> getAllByAuthorId(
            Pageable pageable,
            @PathVariable UUID authorId
    ) {
        return ResponseEntity
                .ok().body(postService.getAllByAuthorId(authorId, pageable));
    }

    @PatchMapping("/{postId}/content")
    public ResponseEntity<PostResponseDTO> updateContent(
            @PathVariable UUID postId,
            @RequestBody UpdateContentPostDTO dto
    ) {
        return ResponseEntity
                .ok().body(postService.updateContent(postId, dto));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteById(
            @PathVariable UUID postId
    ) {
        postService.delete(postId);

        return ResponseEntity
                .noContent().build();
    }
}
