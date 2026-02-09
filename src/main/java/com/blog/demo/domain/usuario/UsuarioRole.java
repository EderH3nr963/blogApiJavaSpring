package com.blog.demo.domain.usuario;

public enum UsuarioRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    UsuarioRole(String role) {
        this.role = role;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
