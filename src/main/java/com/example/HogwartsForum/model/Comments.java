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
public class Comments {

    @Id
    @GeneratedValue
    private Integer id;
    private String commentText;
    private String submissionTime;

    public Comments(String text) {
        this.commentText = text;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        this.submissionTime = dtf.format(dateTimeNow);
    }

}