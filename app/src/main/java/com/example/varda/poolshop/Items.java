package com.example.varda.poolshop;

import java.util.ArrayList;

public class Items {
    private String name;
    private String quantity;

    public Items(String name, String quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public static ArrayList<Items> createItemsList(int numItems) {
        ArrayList<Items> items = new ArrayList<>();

        for (int i = 0; i <= numItems; i++) {
            items.add(new Items("Item " + ++i, "10"));
        }

        return items;
    }
}
