package com.blog.demo.service;

import com.blog.demo.dto.request.LoginRequestDTO;
import com.blog.demo.dto.response.LoginResponseDTO;
import com.blog.demo.dto.response.UsuarioResponseDTO;
import com.blog.demo.security.TokenService;
import com.blog.demo.security.UsuarioDetailsImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthorizationService(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        var authenticateToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authenticate = authenticationManager.authenticate(authenticateToken);

        UsuarioDetailsImpl usuario = (UsuarioDetailsImpl) authenticate.getPrincipal();
        String token = tokenService.generateToken((UsuarioDetailsImpl) authenticate.getPrincipal());

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO(
                usuario.getId().toString(),
                usuario.getUsername(), // Email
                usuario.getProfileUsername(), // Username
                usuario.getRole()
        );

        return new LoginResponseDTO(
                token,
                usuarioResponseDTO
        );
    }
}
