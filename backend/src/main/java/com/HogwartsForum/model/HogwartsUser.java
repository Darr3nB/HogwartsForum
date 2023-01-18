package com.HogwartsForum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HogwartsUser {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Enumerated(EnumType.STRING)
    private HogwartsHouses house;
    @Enumerated(EnumType.STRING)
    private PetTypes pet;
    @Column(nullable = false)
    private int reputation;
    @OneToMany
    private Set<Question> questionsList;
    @OneToMany
    private Set<Comment> commentList;
    private String profilePicture;

    public HogwartsUser(String name, String password, String house, String pet) {
        this.name = name;
        this.password = password;
        this.house = HogwartsHouses.getHouseByStringEquivalent(house);
        this.pet = PetTypes.getPetByStringEquivalent(pet);
    }
}
