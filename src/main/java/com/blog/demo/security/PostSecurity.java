package com.blog.demo.security;

import com.blog.demo.repository.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostSecurity {
    private final PostRepository postRepository;

    public PostSecurity(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public boolean isOwner(UUID postId) {
        UsuarioDetailsImpl user = (UsuarioDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UUID loggedUserId = user.getId();

        return postRepository.existsByIdAndAuthorId(postId, loggedUserId);
    }
}
