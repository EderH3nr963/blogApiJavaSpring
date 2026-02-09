package com.blog.demo.dto.response;

import jakarta.validation.constraints.NotNull;

public record LoginResponseDTO (
        @NotNull String token,
        @NotNull UsuarioResponseDTO usuario
) {}
