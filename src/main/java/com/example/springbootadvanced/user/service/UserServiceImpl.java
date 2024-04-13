package com.example.springbootadvanced.user.service;

import com.example.springbootadvanced.events.OtpGenerateEvent;
import com.example.springbootadvanced.user.User;
import com.example.springbootadvanced.user.UserCreateDto;
import com.example.springbootadvanced.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public User create(UserCreateDto dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        User savedUser = userRepository.save(user);
        publisher.publishEvent(new OtpGenerateEvent(user));
        return savedUser;
    }
}
