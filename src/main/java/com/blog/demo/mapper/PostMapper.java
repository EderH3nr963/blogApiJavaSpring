package com.blog.demo.mapper;

import com.blog.demo.domain.post.Post;
import com.blog.demo.dto.response.AuthorSummaryDTO;
import com.blog.demo.dto.response.PostResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public PostResponseDTO toResponse(Post post) {
        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedAt(),
                new AuthorSummaryDTO(
                        post.getAuthor().getId(),
                        post.getAuthor().getUsername()
                )
        );
    }
}
