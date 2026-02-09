package com.blog.demo.controller;

import com.blog.demo.dto.request.LoginRequestDTO;
import com.blog.demo.dto.request.RegisterRequestDTO;
import com.blog.demo.dto.response.LoginResponseDTO;
import com.blog.demo.service.AuthorizationService;
import com.blog.demo.service.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Autorização",
        description = "Rotas de autorização"
)
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private  final UsuarioService usuarioService;

    public AuthorizationController(AuthorizationService authorizationService, UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/login")
    @SecurityRequirement(name = "")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        LoginResponseDTO loginResponseDTO = authorizationService.login(dto);

        return ResponseEntity.ok().body(loginResponseDTO);
    }

    @PostMapping("/register")
    @SecurityRequirement(name = "")
    public ResponseEntity<Void> login(@RequestBody @Valid RegisterRequestDTO dto) {
        usuarioService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
