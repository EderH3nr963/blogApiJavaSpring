package com.blog.demo.controller;

import com.blog.demo.dto.request.UpdateEmailDTO;
import com.blog.demo.dto.request.UpdatePasswordDTO;
import com.blog.demo.dto.request.UpdateUsernameDTO;
import com.blog.demo.dto.response.UsuarioResponseDTO;
import com.blog.demo.security.UsuarioDetailsImpl;
import com.blog.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Usuário", description = "Rotas de acesso do usuário")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "getMyProfile"
    )
    public ResponseEntity<UsuarioResponseDTO> myProfile(@AuthenticationPrincipal UsuarioDetailsImpl usuario) {
        return ResponseEntity.ok().body(usuarioService.getById(usuario.getId()));
    }

    @PatchMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "updateEmail"
    )
    public ResponseEntity<UsuarioResponseDTO> updateEmail(
            @AuthenticationPrincipal UsuarioDetailsImpl usuario,
            @RequestBody @Valid UpdateEmailDTO dto
    ) {
        return ResponseEntity.ok().body(usuarioService.updateEmail(usuario.getId(), dto.email()));
    }

    @PatchMapping("/username")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            operationId = "updateUsername"
    )
    public ResponseEntity<UsuarioResponseDTO> updateUsername(
            @AuthenticationPrincipal UsuarioDetailsImpl usuario,
            @RequestBody @Valid UpdateUsernameDTO dto
    ) {
        return ResponseEntity.ok().body(usuarioService.updateUsername(usuario.getId(), dto.username()));
    }

    @PatchMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = "updatedPassword"
    )
    public ResponseEntity<Void> updatePassowrd(
            @AuthenticationPrincipal UsuarioDetailsImpl usuario,
            @RequestBody @Valid UpdatePasswordDTO dto
    ) {
        usuarioService.updatePassword(usuario.getId(), dto);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            operationId = "deleteUsuario"
    )
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal UsuarioDetailsImpl usuario
    ) {
        usuarioService.softDelete(usuario.getId());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
