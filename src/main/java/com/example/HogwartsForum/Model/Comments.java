package com.example.HogwartsForum.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue
    private Integer id;
    private String commentText;
    private String submissionTime;

    public Comments(String text){
        this.commentText = text;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        this.submissionTime = dtf.format(dateTimeNow);
    }

}
