package com.example.HogwartsForum.Model;

import com.example.HogwartsForum.Util.HogwartsHouses;

public class User {

    private Integer id;

    private String name;
    private String password;
    private HogwartsHouses house;
    private String pet;

    public User(String name, String password, HogwartsHouses house, String pet) {
        this.name = name;
        this.password = password;
        this.house = house;
        this.pet = pet;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public HogwartsHouses getHouse(){
        return this.house;
    }

    public String getPet(){
        return this.pet;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    @Override
    public String toString(){
        return String.format("Name: %s\nHouse: %s\nPet: %s", this.name, this.house, this.pet);
    }
}
