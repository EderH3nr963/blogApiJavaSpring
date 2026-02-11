package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateContentCommentDTO(
        @NotNull String newContent
) {
}
