package com.example.springbootadvanced.service;

import com.example.springbootadvanced.dto.BlogCreateDto;
import com.example.springbootadvanced.entity.Blog;
import com.example.springbootadvanced.event.BlogDeletedEvent;
import com.example.springbootadvanced.mapper.BlogMapper;
import com.example.springbootadvanced.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {
    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    public Blog create(BlogCreateDto dto) {
        Blog blog = blogMapper.toEntity(dto);
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> saveAll(List<BlogCreateDto> dtos) {
        List<Blog> entities = blogMapper.toEntities(dtos);
        return blogRepository.saveAll(entities);
    }

    @Override
    public boolean edit(BlogCreateDto dto, long id) {
        Blog blog = findById(id);
        blog.setDescription(dto.getDescription());
        blog.setTitle(dto.getTitle());
        blogRepository.save(blog);
        return true;
    }

    @Override
    public boolean delete(long id) {
        Blog blog = findById(id);
        eventPublisher.publishEvent(new BlogDeletedEvent(blog));
        blogRepository.delete(blog);
        return true;
    }
}
