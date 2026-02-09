package com.blog.demo.controller;

import com.blog.demo.dto.request.UpdateBlockedRequestDTO;
import com.blog.demo.dto.request.UpdateEmailDTO;
import com.blog.demo.dto.request.UpdatePasswordDTO;
import com.blog.demo.dto.request.UpdateUsernameDTO;
import com.blog.demo.dto.response.PageUsuariosResponseDTO;
import com.blog.demo.dto.response.UsuarioResponseDTO;
import com.blog.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/users")
@Tag(
        name = "Administração de Usuários",
        description = "Rota para administradores controlarem e administrarem usuários"
)
public class UsuarioAdminController {
    private final UsuarioService usuarioService;

    public UsuarioAdminController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
     public ResponseEntity<UsuarioResponseDTO> getById(
             @PathVariable UUID id
     ) {
        return ResponseEntity.ok().body(usuarioService.getById(id));
    }

    @GetMapping
    public ResponseEntity<PageUsuariosResponseDTO> getAll (Pageable pageable) {
        return ResponseEntity.ok().body(usuarioService.getAll(pageable));
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<UsuarioResponseDTO> updateEmail(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateEmailDTO dto
    ) {
        return ResponseEntity.ok().body(usuarioService.updateEmail(id, dto.email()));
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<UsuarioResponseDTO> updateUsername(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUsernameDTO dto
    ) {
        return ResponseEntity.ok().body(usuarioService.updateUsername(id, dto.username()));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(
            @PathVariable UUID id,
            @RequestBody @Valid UpdatePasswordDTO dto
    ) {
        usuarioService.updatePassword(id, dto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/blocked")
    public ResponseEntity<Void> updateBlocked(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateBlockedRequestDTO dto
    ) {
        usuarioService.updateBlocked(id, dto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {
        usuarioService.softDelete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
