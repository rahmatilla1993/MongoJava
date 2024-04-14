package com.example.springbootadvanced.mapper;

import com.example.springbootadvanced.dto.UserCreateDto;
import com.example.springbootadvanced.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserCreateDto dto);

}
