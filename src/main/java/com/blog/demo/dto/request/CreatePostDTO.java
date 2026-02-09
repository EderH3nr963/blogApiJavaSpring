package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotNull;

public record CreatePostDTO(
    @NotNull String title,
    @NotNull String content
) { }
