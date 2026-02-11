package com.blog.demo.domain.comment;

import com.blog.demo.domain.post.Post;
import com.blog.demo.domain.usuario.Usuario;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String content;

    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = false)
    private Usuario author;

    @ManyToOne()
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @CreatedDate
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Usuario getAuthor() { return author; }
    public void setAuthor(Usuario author) { this.author = author; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
