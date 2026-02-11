package com.blog.demo.dto.response;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentResponseDTO(
        @NotNull UUID id,
        @NotNull String content,
        AuthorSummaryDTO author,
        PostSummaryDTO post
) { }


