package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateCommentDTO (
        @NotNull String content,
        @NotNull UUID postId
) {}
