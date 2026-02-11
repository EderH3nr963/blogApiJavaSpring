package com.blog.demo.controller;

import com.blog.demo.dto.request.CreatePostDTO;
import com.blog.demo.dto.request.UpdateContentPostDTO;
import com.blog.demo.dto.request.UpdateTitlePostDTO;
import com.blog.demo.dto.response.PagePostResponseDTO;
import com.blog.demo.dto.response.PostResponseDTO;
import com.blog.demo.security.UsuarioDetailsImpl;
import com.blog.demo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
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
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            operationId = "createPost"
    )
    public ResponseEntity<PostResponseDTO> create(
            @RequestBody @Valid CreatePostDTO dto,
            @AuthenticationPrincipal UsuarioDetailsImpl authenticateUsuario
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED).body(postService.create(dto, authenticateUsuario.getId()));
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "getPostById"
    )
    public ResponseEntity<PostResponseDTO> getById(
        @PathVariable UUID postId
    ) {
        return ResponseEntity
                .ok().body(postService.getById(postId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "listAllPost"
    )
    public ResponseEntity<PagePostResponseDTO> getAll(
             @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity
                .ok().body(postService.getAll(pageable));
    }

    @GetMapping("/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "listAllPostOfUser"
    )
    public ResponseEntity<PagePostResponseDTO> getAllByAuthorId(
            @PageableDefault(size = 10) Pageable pageable,
            @PathVariable UUID authorId
    ) {
        return ResponseEntity
                .ok().body(postService.getAllByAuthorId(authorId, pageable));
    }

    @PatchMapping("/{postId}/content")
    @Operation(
            operationId = "updatedPostContent"
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PostResponseDTO> updateContent(
            @PathVariable UUID postId,
            @RequestBody UpdateContentPostDTO dto
    ) {
        return ResponseEntity
                .ok().body(postService.updateContent(postId, dto));
    }

    @PatchMapping("/{postId}/title")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "updatedPostTitle"
    )
    public ResponseEntity<PostResponseDTO> updateTitle(
            @PathVariable UUID postId,
            @RequestBody UpdateTitlePostDTO dto
    ) {
        return ResponseEntity
                .ok().body(postService.updateTitle(postId, dto));
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = "deletePost"
    )
    public ResponseEntity<Void> deleteById(
            @PathVariable UUID postId
    ) {
        postService.delete(postId);

        return ResponseEntity
                .noContent().build();
    }
}
