package com.HogwartsForum.model;

public enum PetTypes {
    OWL,
    CAT,
    TOAD,
    RAT,
    FERRET;

    public static PetTypes getPetByStringEquivalent(String petTypeAsString) {
        return switch (petTypeAsString) {
            case "Ferret" -> FERRET;
            case "Cat" -> CAT;
            case "Toad" -> TOAD;
            case "Rat" -> RAT;
            default -> OWL;
        };
    }
}
