package com.example.reactivedemo.service;

import com.example.reactivedemo.model.User;
import com.example.reactivedemo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> createUser(String userName) {
        return this.userRepository.save(new User(userName));
    }

    public Flux<User> getAllUsers() {
        return this.userRepository.findAll().delaySequence(Duration.ofSeconds(3));
    }
}
