package com.blog.demo.mapper;

import com.blog.demo.domain.usuario.Usuario;
import com.blog.demo.dto.response.UsuarioResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId().toString(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getRole()
        );
    }
}
