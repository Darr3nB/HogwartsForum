package com.example.HogwartsForum.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Questions {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String questionText;
    private String submissionTime;
    @OneToMany
    private Set<Comments> commentsList;

    public Questions(String title, String questionText){
        this.title = title;
        this.questionText = questionText;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        this.submissionTime = dtf.format(dateTimeNow);
    }
}
