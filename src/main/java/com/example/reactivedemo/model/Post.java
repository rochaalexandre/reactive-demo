package com.example.reactivedemo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Data
@NoArgsConstructor
public class Post {
    @Id
    private int id;
    private String title;
    private PostStatus status;
    private Integer mediaId;

}
