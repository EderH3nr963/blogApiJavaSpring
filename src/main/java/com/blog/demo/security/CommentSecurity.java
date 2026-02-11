package com.blog.demo.security;

import com.blog.demo.repository.CommentRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommentSecurity {
    private CommentRepository commentRepository;

    public CommentSecurity(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public boolean isOwnerAuthorId(UUID authorId) {
        UsuarioDetailsImpl userDetails  = (UsuarioDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID authorIdAuthorize = userDetails.getId();

        return authorIdAuthorize == authorId;
    }

    public boolean isOwnerComment(UUID commentId) {
        UsuarioDetailsImpl userDetails  = (UsuarioDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID auhtorId = userDetails.getId();

        return commentRepository.existsByIdAndAuthorId(commentId, auhtorId);
    }
}
