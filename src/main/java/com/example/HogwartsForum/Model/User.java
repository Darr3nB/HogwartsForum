package com.example.HogwartsForum.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @OneToMany
    private Integer id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private HogwartsHouses house;
    private String pet;

    public User(String name, String password, HogwartsHouses house, String pet) {
        this.name = name;
        this.password = password;
        this.house = house;
        this.pet = pet;
    }
}
