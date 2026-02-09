package com.blog.demo.dto.response;

import com.blog.demo.domain.post.Post;
import com.blog.demo.mapper.PostMapper;
import org.springframework.data.domain.Page;

import java.util.List;

public record PagePostResponseDTO(
        List<PostResponseDTO> posts,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean last
) {
    public static PagePostResponseDTO from(Page<PostResponseDTO> page) {
        return new PagePostResponseDTO(
                page.getContent(),
                page.getSize(),
                page.getNumber(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast()
        );
    }
}
