package com.blog.demo.repository;

import com.blog.demo.domain.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT p FROM post p WHERE p.deleted = false AND p.id = :postId")
    Optional<Post> findByIdNotDeleted(UUID postId);

    @Query("SELECT p FROM post p WHERE p.deleted = false")
    Page<Post> findAllNotDeleted(Pageable pageable);

    @Query("SELECT p FROM post p WHERE p.author.id = :authorId AND p.deleted = false")
    Page<Post> findByAuthorIdNotDeleted(UUID authorId, Pageable pageable);

    boolean existsByIdAndAuthorId(UUID postId, UUID authorId);
}
