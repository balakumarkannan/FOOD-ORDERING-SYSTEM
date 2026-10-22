package com.restaurant;

/**
 * Represents a single item in the customer's order
 */
public class OrderItem {
    private MenuItem menuItem;
    private int quantity;

    public OrderItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }

    public int getTotalPreparationTime() {
        return menuItem.getPreparationTime() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-30s | Qty: %2d | ₹%.2f per item | Total: ₹%.2f | Prep: %d mins",
            menuItem.getName(), quantity, menuItem.getPrice(), getTotalPrice(), getTotalPreparationTime());
    }
}
