package com.example.HogwartsForum.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HogwartsUser {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private HogwartsHouses house;
    @Enumerated(EnumType.STRING)
    private PetTypes pet;
    @OneToMany
    private Set<Questions> questionsList;

    public HogwartsUser(String name, String password, String house, String pet) {
        this.name = name;
        this.password = password;
        this.house = HogwartsHouses.getHouseByStringEquivalent(house);
        this.pet = PetTypes.getPetByStringEquivalent(pet);
    }
}
