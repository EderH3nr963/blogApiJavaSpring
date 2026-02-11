package com.blog.demo.service;

import com.blog.demo.domain.post.Post;
import com.blog.demo.domain.usuario.Usuario;
import com.blog.demo.dto.request.CreatePostDTO;
import com.blog.demo.dto.request.UpdateContentPostDTO;
import com.blog.demo.dto.request.UpdateTitlePostDTO;
import com.blog.demo.dto.response.PagePostResponseDTO;
import com.blog.demo.dto.response.PostResponseDTO;
import com.blog.demo.mapper.PostMapper;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.repository.UsuarioRepository;
import com.blog.demo.security.PostSecurity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostMapper postMapper;
    private final PostSecurity postSecurity;

    public PostService(PostRepository postRepository, UsuarioRepository usuarioRepository, PostMapper postMapper, PostSecurity postSecurity) {
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
        this.postMapper = postMapper;
        this.postSecurity = postSecurity;
    }

    @Transactional
    public PostResponseDTO create(CreatePostDTO dto, UUID authorId) {
        Usuario usuario = usuarioRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Post post = new Post();
        post.setTitle(dto.title());
        post.setContent(dto.content());
        post.setAuthor(usuario);

        postRepository.save(post);

        return postMapper.toResponse(post);
    }

    public PostResponseDTO getById(UUID id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        return postMapper.toResponse(post);
    }

    public PagePostResponseDTO getAll(Pageable pageable) {
        Page<Post> page = postRepository.findAllNotDeleted(pageable);

        Page<PostResponseDTO> dtoPage = page.map(postMapper::toResponse);

        return PagePostResponseDTO.from(dtoPage);
    }

    public PagePostResponseDTO getAllByAuthorId(UUID authorId, Pageable pageable) {
        Page<Post> page = postRepository.findByAuthorIdNotDeleted(authorId, pageable);

        Page<PostResponseDTO> dtoPage = page.map(postMapper::toResponse);

        return PagePostResponseDTO.from(dtoPage);
    }

    @PreAuthorize("hasRole('ADMIN') or @postSecurity.isOwnerAuthorId(#id)")
    @Transactional
    public PostResponseDTO updateTitle(UUID id, UpdateTitlePostDTO dto) {
        Post post = postRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        post.setTitle(dto.title());

        return postMapper.toResponse(post);
    }

    @PreAuthorize("hasRole('ADMIN') or @postSecurity.isOwner(#id)")
    @Transactional
    public PostResponseDTO updateContent(UUID id, UpdateContentPostDTO dto) {
        Post post = postRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        post.setContent(dto.content());

        return postMapper.toResponse(post);
    }

    @PreAuthorize("hasRole('ADMIN') or @postSecurity.isOwner(#id)")
    @Transactional
    public void delete(UUID id) {
        Post post = postRepository.findByIdNotDeleted(id)
                .orElseThrow(() -> new EntityNotFoundException("Post não encotrado"));

        post.setDeleted(true);
    }
}
