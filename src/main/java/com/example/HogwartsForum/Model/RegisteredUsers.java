package com.example.HogwartsForum.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RegisteredUsers {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private HogwartsHouses house;
    private String pet;
    @OneToMany
    private Set<Questions> questionsList;

    public RegisteredUsers(String name, String password, HogwartsHouses house, String pet) {
        this.name = name;
        this.password = password;
        this.house = house;
        this.pet = pet;
    }
}
