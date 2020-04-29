package com.example.myhamburgerapplication;

public class Item {
    private String calories;
    private String sodium;
    private String sugar;
    private String protein;

    Item(String calories, String sodium, String sugar, String protein) {
        this.calories = calories;
        this.sodium = sodium;
        this.sugar = sugar;
        this.protein = protein;
    }

    public String getCalories() {
        return calories;
    }

    public String getSodium() {
        return sodium;
    }

    public String getSugar() {
        return sugar;
    }

    public String getProtein() {
        return protein;
    }

    public static String[] itemToArray(Item item) {
        String[] array = {item.getCalories(), item.getSodium(), item.getSugar(), item.getProtein()};
        return array;
    }

    // sample data
    public static final Item[] items = {
            new Item("calories: 520", "sodium: 1180", "sugar: 7", "protein: 32"),
            new Item("calories: 730", "sodium: 1280", "sugar: 12", "protein: 40"),
            new Item("calories: 540", "sodium: 790", "sugar: 5", "protein: 28"),
            new Item("calories: 360", "sodium: 800", "sugar: 5", "protein: 15"),
            new Item("calories: 540", "sodium: 940", "sugar: 9", "protein: 25"),
            new Item("calories: 440", "sodium: 710", "sugar: 9", "protein: 26"),
            new Item("calories: 300", "sodium: 680", "sugar: 5", "protein: 14")
    };


}
