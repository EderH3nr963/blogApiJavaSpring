package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateTitlePostDTO (
    @NotNull String title
) { }
