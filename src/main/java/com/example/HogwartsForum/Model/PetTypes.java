package com.example.HogwartsForum.Model;

public enum PetTypes {
    Owl,
    Cat,
    Toad,
    Rat,
    Ferret;

    public static PetTypes getPetByStringEquivalent(String petTypeAsString) {
        return switch (petTypeAsString) {
            case "Ferret" -> Ferret;
            case "Cat" -> Cat;
            case "Toad" -> Toad;
            case "Rat" -> Rat;
            default -> Owl;
        };
    }
}
