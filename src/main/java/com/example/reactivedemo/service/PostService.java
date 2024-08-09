package com.example.reactivedemo.service;

import com.example.reactivedemo.event.MediaCompleteEventBus;
import com.example.reactivedemo.model.Media;
import com.example.reactivedemo.model.Post;
import com.example.reactivedemo.model.PostStatus;
import com.example.reactivedemo.repositories.PostRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MediaCompleteEventBus eventBus;
    private final Sinks.Many<Post> postSink = Sinks.many().multicast().onBackpressureBuffer();


    public PostService(PostRepository postRepository, MediaCompleteEventBus eventBus) {
        this.postRepository = postRepository;
        this.eventBus = eventBus;
        this.eventBus.getEvents().subscribe(event -> {
            this.linkMediaWithPostAndPublish(event.getMedia().getPostId(), event.getMedia());
        });
    }

    public Mono<Post> createPost(String title) {
        Post post = new Post();
        post.setTitle(title);
        post.setStatus(PostStatus.draft);
        return postRepository.save(post);
    }

    public Flux<Post> findAll() {
        return postRepository.findAll().delaySequence(Duration.ofSeconds(3));
    }

    public Flux<Post> findAllPublished() {
        return postRepository.findAllByStatus(Mono.just(PostStatus.publish));
    }

    public void linkMediaWithPostAndPublish(Integer postId, Media media) {
        System.out.println("Calling the link post method");
         postRepository.findById(postId).map(post -> {
             post.setMediaId(media.getId());
             post.setStatus(PostStatus.publish);
             return post;
         }).flatMap(this.postRepository::save).subscribe(postSink::tryEmitNext);
    }

    public Flux<Post> getRecentPublishedPosts() {
        System.out.println("getRecentPublishedPosts");
        return postSink.asFlux();
    }
}
