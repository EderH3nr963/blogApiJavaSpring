package com.blog.demo.dto.response;

import com.blog.demo.domain.usuario.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

public record UsuarioResponseDTO (
        @NotNull @UUID String id,
        @NotNull @Email String email,
        @NotNull String username,
        @NotNull UsuarioRole role
) { }
