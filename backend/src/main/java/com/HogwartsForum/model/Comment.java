package com.HogwartsForum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue
    private Integer id;
    private String commentText;
    private LocalDateTime submissionTime;
    private Integer upVoteCount;
    private Integer downVoteCount;
    private String image;

    public Comment(String commentText, String image){
        this.commentText = commentText;
        this.image = image;
        this.submissionTime = LocalDateTime.now();
    }
}
