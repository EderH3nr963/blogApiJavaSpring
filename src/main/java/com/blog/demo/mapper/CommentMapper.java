package com.blog.demo.mapper;

import com.blog.demo.domain.comment.Comment;
import com.blog.demo.dto.response.AuthorSummaryDTO;
import com.blog.demo.dto.response.CommentResponseDTO;
import com.blog.demo.dto.response.PostSummaryDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentResponseDTO toResponse(Comment comment) {
        return new CommentResponseDTO(
                comment.getId(),
                comment.getContent(),
                new AuthorSummaryDTO(
                        comment.getAuthor().getId(),
                        comment.getAuthor().getUsername()
                ),
                new PostSummaryDTO(
                        comment.getPost().getId(),
                        comment.getPost().getTitle()
                )
        );
    }
}
