package com.blog.demo.dto.response;

import java.time.LocalDateTime;

public record ApiErrorDTO(
        int status,
        String message,
        LocalDateTime timestamp,
        String path
) {
    public ApiErrorDTO(int status, String message, String path) {
        this(status, message, LocalDateTime.now(), path);
    }
}
