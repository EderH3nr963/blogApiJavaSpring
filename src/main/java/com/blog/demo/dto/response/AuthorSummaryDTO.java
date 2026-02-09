package com.blog.demo.dto.response;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AuthorSummaryDTO(
        UUID id,
        @NotNull String username
) { }
