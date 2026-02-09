package com.blog.demo.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUsernameDTO(
        @NotBlank String username
) { }
