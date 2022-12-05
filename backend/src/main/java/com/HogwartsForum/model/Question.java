package com.HogwartsForum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany
    private Set<Comment> commentList;


    public Question(String title, String questionText) {
        this.title = title;
        this.questionText = questionText;
        this.submissionTime = LocalDateTime.now();
    }
}
