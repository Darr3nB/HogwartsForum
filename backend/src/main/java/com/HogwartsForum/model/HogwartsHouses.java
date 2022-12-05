package com.HogwartsForum.model;

public enum HogwartsHouses {
    GRYFFINDOR,
    SLYTHERIN,
    HUFFLEPUFF,
    RAVENCLAW;

    public static HogwartsHouses getHouseByStringEquivalent(String houseAsString) {
        return switch (houseAsString) {
            case "Gryffindor" -> GRYFFINDOR;
            case "Slytherin" -> SLYTHERIN;
            case "Hufflepuff" -> HUFFLEPUFF;
            default -> RAVENCLAW;
        };
    }
}
