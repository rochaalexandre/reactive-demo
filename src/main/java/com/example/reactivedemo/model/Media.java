package com.example.reactivedemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Data
@NoArgsConstructor
public class Media {
    @Id
    private int id;
    private MediaType type;
    private Integer postId;

    @MappedCollection(idColumn = "post_id", keyColumn = "id")
    private Post post;
}
