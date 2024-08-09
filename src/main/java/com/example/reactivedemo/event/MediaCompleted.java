package com.example.reactivedemo.event;

import com.example.reactivedemo.model.Media;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MediaCompleted {
    private Media media;
}
