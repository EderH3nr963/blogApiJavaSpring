package com.blog.demo.service;

import com.blog.demo.domain.usuario.Usuario;
import com.blog.demo.dto.request.RegisterRequestDTO;
import com.blog.demo.dto.request.UpdateBlockedRequestDTO;
import com.blog.demo.dto.request.UpdatePasswordDTO;
import com.blog.demo.dto.response.PageUsuariosResponseDTO;
import com.blog.demo.dto.response.UsuarioResponseDTO;
import com.blog.demo.exception.BusinessException;
import com.blog.demo.mapper.UsuarioMapper;
import com.blog.demo.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(RegisterRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email())) {
            throw new BusinessException("Email já cadastrado");
        }

        if (usuarioRepository.existsByUsername(dto.username())) {
            throw new BusinessException("Nome de usuário já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setUsername(dto.username());
        usuario.setPassword(passwordEncoder.encode(dto.password()));

        usuarioRepository.save(usuario);
    }

    public UsuarioResponseDTO getById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return usuarioMapper.toResponse(usuario);
    }

    public PageUsuariosResponseDTO getAll(Pageable pageable) {
        Page<Usuario> page = usuarioRepository.findAllByDeletedFalse(pageable);

        return new PageUsuariosResponseDTO(
                page.getContent().stream()
                        .map(usuarioMapper::toResponse).toList(),
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }

    @Transactional
    public UsuarioResponseDTO updateEmail(UUID id, String email) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new BusinessException("Email já cadastrado");
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setEmail(email);

        return usuarioMapper.toResponse(usuario);
    }

    @Transactional
    public UsuarioResponseDTO updateUsername(UUID id, String username) {
        if (usuarioRepository.existsByUsername(username)) {
            throw new BusinessException("Nome de usuário já cadastrado");
        }

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setUsername(username);

        return usuarioMapper.toResponse(usuario);
    }

    @Transactional
    public void updatePassword(UUID id, UpdatePasswordDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.setPassword(passwordEncoder.encode(dto.password()));
    }

    @Transactional
    public void updateBlocked(UUID id, UpdateBlockedRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuario.setBlocked(dto.blocked());
    }

    @Transactional
    public void softDelete(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        usuario.setUsername(usuario.getUsername() + "#deleted_" + usuario.getId().toString());
        usuario.setEmail(usuario.getEmail() + "#deleted_" + usuario.getId().toString());
        usuario.setDeleted(true);
    }
}
