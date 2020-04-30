package com.example.myhamburgerapplication;

public class Item {
    private String item1;
    private String item2;
    private String item3;
    private String item4;

    Item(String item1, String item2, String item3, String item4) {
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
    }

    public String getItem1() {
        return item1;
    }

    public String getItem2() {
        return item2;
    }

    public String getItem3() {
        return item3;
    }

    public String getItem4() {
        return item4;
    }

    public static String[] itemToArray(Item item) {
        String[] array = {item.getItem1(), item.getItem2(), item.getItem3(), item.getItem4()};
        return array;
    }

    // sample data
    public static final Item[] items = {
            new Item("Triple Cheeseburger", "Bacon Clubhouse Burger", "Double Filet-O-Fish", "Buffalo Ranch McChicken"),
            new Item("Coca-Cola", "Chocolate Shake", "Iced Tea", "Sprite"),
            new Item("Egg McMuffin", "Sausage McMuffin", "Sausage Biscuit", "Fruit & Maple Oatmeal"),
    };

    /*
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
*/

}
