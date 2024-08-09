package com.example.reactivedemo.repositories;

import com.example.reactivedemo.model.Post;
import com.example.reactivedemo.model.PostStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostRepository extends ReactiveCrudRepository<Post, Integer> {

    Flux<Post> findAllByStatus(Mono<PostStatus> status);
}
