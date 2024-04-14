package com.example.springbootadvanced.mapper;

import com.example.springbootadvanced.dto.BlogCreateDto;
import com.example.springbootadvanced.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BlogMapper {

    Blog toEntity(BlogCreateDto dto);

    List<Blog> toEntities(List<BlogCreateDto> dtos);

}
