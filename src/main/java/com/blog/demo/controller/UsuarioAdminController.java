package com.blog.demo.controller;

import com.blog.demo.dto.request.UpdateBlockedRequestDTO;
import com.blog.demo.dto.request.UpdateEmailDTO;
import com.blog.demo.dto.request.UpdatePasswordDTO;
import com.blog.demo.dto.request.UpdateUsernameDTO;
import com.blog.demo.dto.response.PageUsuariosResponseDTO;
import com.blog.demo.dto.response.UsuarioResponseDTO;
import com.blog.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
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
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "adminGetUsuarioById"
    )
     public ResponseEntity<UsuarioResponseDTO> getById(
             @PathVariable UUID id
     ) {
        return ResponseEntity.ok().body(usuarioService.getById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "adminListAllUsuarios"
    )
    public ResponseEntity<PageUsuariosResponseDTO> getAll (Pageable pageable) {
        return ResponseEntity.ok().body(usuarioService.getAll(pageable));
    }

    @PatchMapping("/{id}/email")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "adminUpdateEmail"
    )
    public ResponseEntity<UsuarioResponseDTO> updateEmail(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateEmailDTO dto
    ) {
        return ResponseEntity.ok().body(usuarioService.updateEmail(id, dto.email()));
    }

    @PatchMapping("/{id}/username")
    @Operation(
            operationId = "adminUpdateUsername"
    )
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<UsuarioResponseDTO> updateUsername(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateUsernameDTO dto
    ) {
        return ResponseEntity.ok().body(usuarioService.updateUsername(id, dto.username()));
    }

    @PatchMapping("/{id}/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = "adminUpdatePassword"
    )
    public ResponseEntity<Void> updatePassword(
            @PathVariable UUID id,
            @RequestBody @Valid UpdatePasswordDTO dto
    ) {
        usuarioService.updatePassword(id, dto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/blocked")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = "adminUpdateBlocked"
    )
    public ResponseEntity<Void> updateBlocked(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateBlockedRequestDTO dto
    ) {
        usuarioService.updateBlocked(id, dto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = "adminDeleteUsuario"
    )
    public ResponseEntity<Void> delete(
            @PathVariable UUID id
    ) {
        usuarioService.softDelete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
