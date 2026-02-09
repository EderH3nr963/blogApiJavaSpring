package com.blog.demo.dto.response;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostResponseDTO(
        UUID id,
        @NotNull String title,
        @NotNull String content,
        AuthorSummaryDTO author
) { }
