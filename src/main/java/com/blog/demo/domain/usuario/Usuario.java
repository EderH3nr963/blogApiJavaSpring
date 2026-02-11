package com.blog.demo.domain.usuario;

import com.blog.demo.domain.comment.Comment;
import com.blog.demo.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UsuarioRole role = UsuarioRole.USER;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<Post>();

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();

    @Column
    private Boolean blocked = false;

    @Column
    private Boolean deleted = false;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public UsuarioRole getRole() { return role; }
    public void setRole(UsuarioRole role) { this.role = role; }

    public Boolean getBlocked() { return blocked; }
    public void setBlocked(Boolean blocked) { this.blocked = blocked; }

    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public List<Post> getPosts() { return posts; }

    public List<Comment> getComments() { return comments; }
}
