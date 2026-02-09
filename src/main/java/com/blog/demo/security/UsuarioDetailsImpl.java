package com.blog.demo.security;

import com.blog.demo.domain.usuario.Usuario;
import com.blog.demo.domain.usuario.UsuarioRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UsuarioDetailsImpl implements UserDetails {
    private final Usuario usuario;

    public UsuarioDetailsImpl(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (usuario.getRole() == UsuarioRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public UUID getId() { return usuario.getId(); }

    @Override
    public String getUsername() { return usuario.getEmail(); }

    public String getProfileUsername() { return usuario.getUsername(); }

    @Override
    public String getPassword() { return usuario.getPassword();}

    public UsuarioRole getRole() { return usuario.getRole(); }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return !(usuario.getBlocked()); }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return !usuario.getDeleted(); }
}
