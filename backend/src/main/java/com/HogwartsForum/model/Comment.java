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
    private String comment;
    private LocalDateTime submissionTime;
    @Column(columnDefinition = "int default 0")
    private Integer upVoteCount;
    @Column(columnDefinition = "int default 0")
    private Integer downVoteCount;
    private String image;
}
