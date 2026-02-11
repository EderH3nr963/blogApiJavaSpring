package com.blog.demo.repository;

import com.blog.demo.domain.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
    @Query("SELECT c FROM comment c WHERE c.author.id = :authorId")
    Page<Comment> findAllCommentByIdAuthor(@Param("authorId") UUID author_id, Pageable pageable);

    @Query("SELECT c FROM comment c WHERE c.post.id = :postId")
    Page<Comment> findAllCommentByIdPost(@Param("postId") UUID postId, Pageable pageable);

    boolean existsByIdAndAuthorId(UUID commentId, UUID authorId);
}
