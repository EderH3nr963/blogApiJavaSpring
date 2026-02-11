package com.blog.demo.dto.response;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PostSummaryDTO(
        @NotNull UUID id,
        @NotNull String title
) {}