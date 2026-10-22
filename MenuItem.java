package com.restaurant;

/**
 * Represents a menu item in the restaurant ordering system
 */
public class MenuItem {
    private int id;
    private String name;
    private String category;
    private double price;
    private int preparationTime; // in minutes

    public MenuItem(int id, String name, String category, double price, int preparationTime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.preparationTime = preparationTime;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    @Override
    public String toString() {
        return String.format("%-2d | %-30s | %-12s | ₹%-8.2f | %d mins", 
            id, name, category.toUpperCase(), price, preparationTime);
    }

    public String toDetailedString() {
        return String.format("%d. %s (%s) - ₹%.2f - Prep Time: %d mins", 
            id, name, category, price, preparationTime);
    }
}
