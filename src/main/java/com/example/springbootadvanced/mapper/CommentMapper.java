package com.example.springbootadvanced.mapper;

import com.example.springbootadvanced.dto.CommentCreateDto;
import com.example.springbootadvanced.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    Comment toEntity(CommentCreateDto dto);
}
