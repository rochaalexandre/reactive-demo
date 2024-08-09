package com.example.reactivedemo.service;

import com.example.reactivedemo.event.MediaCompleteEventBus;
import com.example.reactivedemo.event.MediaCompleted;
import com.example.reactivedemo.model.Media;
import com.example.reactivedemo.model.MediaType;
import com.example.reactivedemo.repositories.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final MediaCompleteEventBus eventBus;

    public Mono<Media> createMedia(String type, Integer postId) {
        var mediaType = MediaType.valueOf(type);
        Media media = new Media();
        media.setType(mediaType);
        media.setPostId(postId);

        return mediaRepository.save(media).map(m ->  {
            System.out.println("Media completed");
            this.eventBus.publish(new MediaCompleted(m));
            return m;
        });
    }

}
