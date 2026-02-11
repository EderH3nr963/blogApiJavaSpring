package com.blog.demo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO (
        @NotNull @Email String email,
        @NotNull String username,
        @NotNull @Size(min = 8) String password
)
{}
