package com.example.reactivedemo.controller;

import com.example.reactivedemo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.reactivedemo.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody Map<String, String> payload) {
        if (payload.containsKey("userName")) {
            return userService.createUser(payload.get("userName"));

        }
        return Mono.empty();
    }
}
