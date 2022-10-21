package com.example.HogwartsForum.model;

public enum HogwartsHouses {
    Gryffindor,
    Slytherin,
    Hufflepuff,
    Ravenclaw;

    public static HogwartsHouses getHouseByStringEquivalent(String houseAsString){
        return switch (houseAsString) {
            case "Gryffindor" -> Gryffindor;
            case "Slytherin" -> Slytherin;
            case "Hufflepuff" -> Hufflepuff;
            default -> Ravenclaw;
        };
    }
}
