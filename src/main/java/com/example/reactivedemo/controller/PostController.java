package com.example.reactivedemo.controller;

import com.example.reactivedemo.model.Post;
import com.example.reactivedemo.model.User;
import com.example.reactivedemo.service.PostService;
import com.example.reactivedemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @GetMapping(path = "all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Post> getAllPosts() {
        return this.postService.findAll();
    }

    @GetMapping(path = "published", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Post> getAllPublishedPosts() {
        return this.postService.findAllPublished().concatWith(this.postService.getRecentPublishedPosts());
    }

    @PostMapping
    public Mono<Post> createPost(@RequestBody Map<String, String> payload) {
        if (payload.containsKey("title")) {
            return postService.createPost(payload.get("title"));
        }

        return Mono.empty();
    }
}
