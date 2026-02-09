package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateBlockedRequestDTO (
    @NotBlank Boolean blocked
) {}
