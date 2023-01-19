package com.HogwartsForum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String questionText;
    private LocalDateTime submissionTime;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comment> commentList;
    private String image;


    public Question(String title, String questionText) {
        this.title = title;
        this.questionText = questionText;
        this.submissionTime = LocalDateTime.now();
    }
}
