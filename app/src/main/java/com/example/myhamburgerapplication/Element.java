package com.example.myhamburgerapplication;

public class Element {
    private String element;
    private Item items;

    private Element(String element, Item items) {
        this.element = element;
        this.items = items;
    }

    public String getElement() {
        return element;
    }

    public Item getItems() {
        return items;
    }

    // sample data
    public static final Element[] sample_data = {
            new Element("Triple Cheeseburger", Item.items[0]),
            new Element("Bacon Clubhouse Burger", Item.items[1]),
            new Element("Double Filet-O-Fish", Item.items[2]),
            new Element("Buffalo Ranch McChicken", Item.items[3]),
            new Element("Big Mac", Item.items[4]),
            new Element("Quarter-Pounder", Item.items[5]),
            new Element("Cheeseburger", Item.items[6])
    };

}
