package com.example.springbootadvanced.user.service;

import com.example.springbootadvanced.user.User;
import com.example.springbootadvanced.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final UserRepository userRepository;

    @Override
    public void generateOtp(User user) {
        user.setOtp(UUID.randomUUID().toString());
        userRepository.save(user);
    }
}
