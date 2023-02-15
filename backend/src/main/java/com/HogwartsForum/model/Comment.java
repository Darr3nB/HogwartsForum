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
    @Column(nullable = false)
    private int upVoteCount;
    @Column(nullable = false)
    private int downVoteCount;
    @Column(columnDefinition = "VARCHAR")
    private String image;

    public boolean validText() {
        return this.commentText.length() >= 5;
    }
}
