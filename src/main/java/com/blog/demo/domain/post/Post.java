package com.blog.demo.domain.post;

import com.blog.demo.domain.comment.Comment;
import com.blog.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario author;

    @Column
    private Boolean deleted = false;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<Comment>();

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public void setAuthor(Usuario author) { this.author = author; }
    public Usuario getAuthor() { return author; }

    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public List<Comment> getComments() { return comments; }
}
