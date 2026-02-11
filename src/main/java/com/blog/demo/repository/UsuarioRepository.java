package com.blog.demo.repository;

import com.blog.demo.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByIdAndDeletedFalse(UUID id);

    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUsername(String username);
    Page<Usuario> findAllByDeletedFalse(Pageable pageable);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
