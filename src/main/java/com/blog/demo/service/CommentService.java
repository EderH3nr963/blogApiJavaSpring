package com.blog.demo.service;

import com.blog.demo.domain.comment.Comment;
import com.blog.demo.domain.post.Post;
import com.blog.demo.domain.usuario.Usuario;
import com.blog.demo.dto.request.CreateCommentDTO;
import com.blog.demo.dto.request.UpdateContentCommentDTO;
import com.blog.demo.dto.response.CommentResponseDTO;
import com.blog.demo.dto.response.PageCommentResponseDTO;
import com.blog.demo.mapper.CommentMapper;
import com.blog.demo.repository.CommentRepository;
import com.blog.demo.repository.PostRepository;
import com.blog.demo.repository.UsuarioRepository;
import com.blog.demo.security.CommentSecurity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UsuarioRepository usuarioRepository;
    private PostRepository postRepository;
    private CommentMapper commentMapper;
    private CommentSecurity commentSecurity;

    public CommentService(
            CommentRepository commentRepository,
            UsuarioRepository usuarioRepository,
            PostRepository postRepository,
            CommentMapper commentMapper,
            CommentSecurity commentSecurity
    ) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
        this.commentMapper = commentMapper;
        this.commentSecurity = commentSecurity;
    }

    public CommentResponseDTO create(UUID authorId, CreateCommentDTO dto) {
        Usuario author = usuarioRepository.findById(authorId)
                .orElseThrow(() -> new EntityNotFoundException("Autor do post não encontrado"));
        Post post = postRepository.findByIdNotDeleted(dto.postId())
                .orElseThrow(() -> new EntityNotFoundException("Post de publicação de comentário não encontrado"));

        Comment comment = new Comment();
        comment.setContent(dto.content());
        comment.setAuthor(author);
        comment.setPost(post);

        commentRepository.save(comment);

        return commentMapper.toResponse(comment);
    }

    public CommentResponseDTO getById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado"));

        return commentMapper.toResponse(comment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public PageCommentResponseDTO getAll(Pageable pageable) {
        Page<Comment> page = commentRepository.findAll(pageable);

        Page<CommentResponseDTO> pageDto = page.map(commentMapper::toResponse);

        return PageCommentResponseDTO.from(pageDto);
    }

    public PageCommentResponseDTO getAllCommentByPostId(Pageable pageable, UUID postId) {
        Page<Comment> page = commentRepository.findAllCommentByIdPost(postId, pageable);

        Page<CommentResponseDTO> pageDto = page.map(commentMapper::toResponse);

        return PageCommentResponseDTO.from(pageDto);
    }

    @PreAuthorize("hasRole('ADMIN') or @commentSecurity.isOwnerAuthorId(#authorId)")
    public PageCommentResponseDTO getAllCommentByAuthorId(Pageable pageable, UUID authorId) {
        Page<Comment> page = commentRepository.findAllCommentByIdAuthor(authorId, pageable);

        Page<CommentResponseDTO> pageDto = page.map(commentMapper::toResponse);

        return PageCommentResponseDTO.from(pageDto);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @commentSecurity.isOwnerComment(#commentId)")
    public CommentResponseDTO updateCommentContent(UUID commentId, UpdateContentCommentDTO dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado"));

        comment.setContent(dto.newContent());

        return commentMapper.toResponse(comment);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN') or @commentSecurity.isOwnerComment(#commentId)")
    public void deleteCommentById(UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado"));

        commentRepository.delete(comment);
    }
}
