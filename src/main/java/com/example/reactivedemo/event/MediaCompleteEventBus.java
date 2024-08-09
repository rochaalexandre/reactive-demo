package com.example.reactivedemo.event;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class MediaCompleteEventBus {
    private final Sinks.Many<MediaCompleted> sink = Sinks.many().multicast().onBackpressureBuffer();


    public void publish(MediaCompleted event) {
        sink.tryEmitNext(event);
    }

    public Flux<MediaCompleted> getEvents() {
        return sink.asFlux();
    }
}

