package com.blog.demo.dto.response;

import java.util.List;

public record PageUsuariosResponseDTO(
        List<UsuarioResponseDTO> usuarios,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
}
