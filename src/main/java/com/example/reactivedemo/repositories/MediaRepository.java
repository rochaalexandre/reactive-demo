package com.example.reactivedemo.repositories;

import com.example.reactivedemo.model.Media;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MediaRepository extends ReactiveCrudRepository<Media, Integer> {

}
