package com.example.springbootadvanced.service;

import com.example.springbootadvanced.dto.UserCreateDto;
import com.example.springbootadvanced.entity.User;
import com.example.springbootadvanced.event.UserCreatedEvent;
import com.example.springbootadvanced.event.UserUpdatedEvent;
import com.example.springbootadvanced.mapper.UserMapper;
import com.example.springbootadvanced.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final UserMapper userMapper;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User create(UserCreateDto dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(savedUser));
        return savedUser;
    }

    @Override
    public boolean edit(UserCreateDto dto, long id) {
        User user = findById(id);
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        userRepository.save(user);
        eventPublisher.publishEvent(new UserUpdatedEvent(user));
        return true;
    }

    @Override
    public boolean delete(long id) {
        User user = findById(id);
        userRepository.delete(user);
        return true;
    }
}
