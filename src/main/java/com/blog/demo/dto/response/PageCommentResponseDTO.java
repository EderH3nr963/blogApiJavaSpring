package com.blog.demo.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageCommentResponseDTO(
        List<CommentResponseDTO> posts,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public static PageCommentResponseDTO from(Page<CommentResponseDTO> page) {
        return new PageCommentResponseDTO(
                page.getContent(),
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
