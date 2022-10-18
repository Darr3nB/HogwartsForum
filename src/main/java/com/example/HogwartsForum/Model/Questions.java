package com.example.HogwartsForum.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Questions(String title, String questionText){
        this.title = title;
        this.questionText = questionText;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        this.submissionTime = dtf.format(dateTimeNow);
    }
}
