package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdatePasswordDTO(
        @Size(min = 8, max = 17) @NotBlank() String password
) {
}
