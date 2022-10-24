package com.example.HogwartsForum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;
    private String comment;
    private LocalDateTime submissionTime;

    public Comment(String text) {
        this.comment = text;
        this.submissionTime = LocalDateTime.now();
    }
}
