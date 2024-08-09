package com.example.reactivedemo.controller;

import com.example.reactivedemo.model.Media;
import com.example.reactivedemo.service.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

//    @GetMapping(path = "all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Post> getAllPosts() {
//        return this.mediaService.
//    }

    @PostMapping
    public Mono<Media> createMedia(@RequestBody Map<String, String> payload) {
        if (payload.containsKey("type")) {
            return this.mediaService.createMedia(payload.get("type"), Integer.parseInt(payload.get("postId")));
        }

        return Mono.empty();
    }
}
