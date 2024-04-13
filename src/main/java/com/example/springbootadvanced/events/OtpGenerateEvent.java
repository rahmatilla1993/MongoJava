package com.example.springbootadvanced.events;

import com.example.springbootadvanced.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class OtpGenerateEvent {
    private final User user;
}
