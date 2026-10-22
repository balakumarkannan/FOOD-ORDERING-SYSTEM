package com.restaurant;

import java.util.*;

/**
 * Represents a customer's order
 */
public class Order {
    private Map<Integer, OrderItem> items; // itemId -> OrderItem
    private static final double GST_RATE = 0.05; // 5% GST

    public Order() {
        this.items = new LinkedHashMap<>();
    }

    public void addItem(MenuItem menuItem, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (items.containsKey(menuItem.getId())) {
            items.get(menuItem.getId()).increaseQuantity();
        } else {
            items.put(menuItem.getId(), new OrderItem(menuItem, quantity));
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public void updateItemQuantity(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                removeItem(itemId);
            } else {
                items.get(itemId).setQuantity(quantity);
            }
        }
    }

    public OrderItem getItem(int itemId) {
        return items.get(itemId);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public double getSubtotal() {
        return items.values().stream()
            .mapToDouble(OrderItem::getTotalPrice)
            .sum();
    }

    public double getGST() {
        return getSubtotal() * GST_RATE;
    }

    public double getGrandTotal() {
        return getSubtotal() + getGST();
    }

    public int getTotalPreparationTime() {
        return items.values().stream()
            .mapToInt(OrderItem::getTotalPreparationTime)
            .max()
            .orElse(0);
    }

    public void clear() {
        items.clear();
    }

    public int getItemCount() {
        return items.size();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Order is empty";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("=".repeat(100)).append("\n");
        sb.append("ORDER SUMMARY\n");
        sb.append("=".repeat(100)).append("\n");

        items.values().forEach(item -> sb.append(item).append("\n"));

        sb.append("-".repeat(100)).append("\n");
        return sb.toString();
    }
}
