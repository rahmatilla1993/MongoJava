package com.example.springbootadvanced.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public final class SendMailEvent {
    private final String otp;
    private final String mail;
}
