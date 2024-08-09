package com.example.reactivedemo.repositories;

import com.example.reactivedemo.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, Integer> {

}
