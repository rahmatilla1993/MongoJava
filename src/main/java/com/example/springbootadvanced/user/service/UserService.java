package com.example.springbootadvanced.user.service;

import com.example.springbootadvanced.user.User;
import com.example.springbootadvanced.user.UserCreateDto;

public interface UserService {

    User create(UserCreateDto dto);
}
