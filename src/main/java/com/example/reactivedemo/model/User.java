package com.example.reactivedemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Setter
@Getter
@Table("users") // using plural to avoid conflict with database reserved word
public class User {

    @Id
    private int id;

    @Column
    private String userName;

    public User(String userName) {
        this.userName = userName;
    }

}
