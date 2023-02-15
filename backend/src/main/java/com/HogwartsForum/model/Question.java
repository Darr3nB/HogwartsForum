package com.HogwartsForum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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
    @Column(columnDefinition = "timestamp default current_timestamp")
    @CreationTimestamp
    @JsonFormat(pattern = "YYYY.MM.dd HH:mm")
    private LocalDateTime submissionTime;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Comment> commentList;
    @Column(columnDefinition = "VARCHAR")
    private String image;
}
