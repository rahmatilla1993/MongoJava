package com.example.springbootadvanced.event;

import com.example.springbootadvanced.entity.User;

public record UserUpdatedEvent(User user) {
}
