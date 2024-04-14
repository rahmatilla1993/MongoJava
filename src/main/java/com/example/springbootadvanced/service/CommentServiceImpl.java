package com.example.springbootadvanced.service;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.entity.Blog;
import com.example.springbootadvanced.entity.Comment;
import com.example.springbootadvanced.mapper.CommentMapper;
import com.example.springbootadvanced.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BlogService blogService;
    private final CommentMapper commentMapper;

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> findAllByBlog(long blogId) {
        Blog blog = blogService.findById(blogId);
        return commentRepository.findAllByBlog(blog);
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    @Override
    public Comment create(CommentCreateDto dto) {
        Comment comment = commentMapper.toEntity(dto);
        Blog blog = blogService.findById(dto.getBlogId());
        comment.setBlog(blog);
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> saveAll(List<CommentCreateDto> dtos) {
        List<Comment> comments = dtos.stream()
                .map(dto -> {
                    Blog blog = blogService.findById(dto.getBlogId());
                    Comment comment = commentMapper.toEntity(dto);
                    comment.setBlog(blog);
                    return comment;
                })
                .toList();
        return commentRepository.saveAll(comments);
    }

    @Override
    public boolean edit(CommentCreateDto dto, long id) {
        Comment comment = findById(id);
        comment.setMessage(dto.getMessage());
        comment.setAuthor(dto.getAuthor());
        Blog blog = blogService.findById(dto.getBlogId());
        comment.setBlog(blog);
        commentRepository.save(comment);
        return true;
    }

    @Override
    public boolean delete(long id) {
        commentRepository.deleteById(id);
        return true;
    }

    @Override
    public void deleteCommentsByBlog(Blog blog) {
        List<Comment> comments = commentRepository.findAllByBlog(blog);
        commentRepository.deleteAll(comments);
    }
}
