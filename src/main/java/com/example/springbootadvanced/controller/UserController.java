package com.example.springbootadvanced.controller;

import com.example.springbootadvanced.dto.UserCreateDto;
import com.example.springbootadvanced.entity.User;
import com.example.springbootadvanced.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public HttpEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    public HttpEntity<User> save(@RequestBody UserCreateDto dto) {
        return ResponseEntity.ok(userService.create(dto));
    }

    @PutMapping("/{id}")
    public HttpEntity<Boolean> edit(@PathVariable("id") long id, @RequestBody UserCreateDto dto) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userService.edit(dto, id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<Boolean> delete(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
