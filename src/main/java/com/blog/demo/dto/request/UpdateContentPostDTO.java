package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateContentPostDTO (
        @NotNull
        @Size(min=6)
        String content
) { }
