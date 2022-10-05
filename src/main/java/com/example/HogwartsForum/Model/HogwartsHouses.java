package com.example.HogwartsForum.Model;

public enum HogwartsHouses {
    Gryffindor,
    Slytherin,
    Hufflepuff,
    Ravenclaw;

    public HogwartsHouses getHouseByStringEquivalent(String houseAsString){
        return switch (houseAsString) {
            case "gryffindor" -> Gryffindor;
            case "slytherin" -> Slytherin;
            case "hufflepuff" -> Hufflepuff;
            default -> Ravenclaw;
        };
    }
}
