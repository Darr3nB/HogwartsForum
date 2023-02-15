package com.HogwartsForum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(columnDefinition = "timestamp default current_timestamp")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY.MM.dd HH:mm")
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
