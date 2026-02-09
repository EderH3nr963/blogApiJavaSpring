package com.blog.demo.repository;

import com.blog.demo.domain.post.Post;
import com.blog.demo.dto.response.PostResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    Page<Post> findByAuthorId(UUID authorId, Pageable pageable);

    boolean existsByIdAndAuthorId(UUID postId, UUID authorId);
}
