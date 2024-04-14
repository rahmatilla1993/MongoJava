package com.example.springbootadvanced.listener;

import com.example.springbootadvanced.entity.User;
import com.example.springbootadvanced.event.UserCreatedEvent;
import com.example.springbootadvanced.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserCreatedEventListener {
    private final UserRepository userRepository;

    @EventListener({UserCreatedEvent.class})
    public void createUserEventListener(UserCreatedEvent event) {
        User user = event.user();
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("User created : {}", user);
    }
}
