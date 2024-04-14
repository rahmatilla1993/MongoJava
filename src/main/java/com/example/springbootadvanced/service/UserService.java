package com.example.springbootadvanced.service;

import com.example.springbootadvanced.dto.UserCreateDto;
import com.example.springbootadvanced.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User findById(long id);
    User create(UserCreateDto dto);
    boolean edit(UserCreateDto dto, long id);
    boolean delete(long id);
}
