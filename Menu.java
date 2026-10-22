lpackage com.restaurant;

import java.util.*;

/**
 * Manages the restaurant menu and provides search/filter functionality
 */
public class Menu {
    private List<MenuItem> items;

    public Menu() {
        this.items = new ArrayList<>();
        initializeMenuItems();
    }

    private void initializeMenuItems() {
        // Appetizers
        items.add(new MenuItem(1, "Paneer Tikka", "appetizers", 250, 15));
        items.add(new MenuItem(2, "Spring Rolls", "appetizers", 150, 10));
        items.add(new MenuItem(3, "Samosa", "appetizers", 80, 8));
        items.add(new MenuItem(4, "Chicken 65", "appetizers", 280, 12));
        items.add(new MenuItem(5, "Tandoori Momos", "appetizers", 180, 10));
        items.add(new MenuItem(6, "Garlic Bread", "appetizers", 120, 8));

        // Mains
        items.add(new MenuItem(7, "Butter Chicken", "mains", 400, 25));
        items.add(new MenuItem(8, "Biryani", "mains", 300, 20));
        items.add(new MenuItem(9, "Chole Bhature", "mains", 200, 18));
        items.add(new MenuItem(10, "Rogan Josh", "mains", 380, 22));
        items.add(new MenuItem(11, "Tandoori Chicken", "mains", 420, 28));
        items.add(new MenuItem(12, "Palak Paneer", "mains", 280, 16));
        items.add(new MenuItem(13, "Dal Makhani", "mains", 200, 20));
        items.add(new MenuItem(14, "Chicken Tikka Masala", "mains", 380, 24));

        // Desserts
        items.add(new MenuItem(15, "Gulab Jamun", "desserts", 120, 5));
        items.add(new MenuItem(16, "Kheer", "desserts", 100, 10));
        items.add(new MenuItem(17, "Jalebi", "desserts", 90, 7));
        items.add(new MenuItem(18, "Rasgulla", "desserts", 110, 8));
        items.add(new MenuItem(19, "Ice Cream", "desserts", 80, 2));
        items.add(new MenuItem(20, "Barfi", "desserts", 100, 5));

        // Beverages
        items.add(new MenuItem(21, "Coca Cola (330ml)", "beverages", 60, 2));
        items.add(new MenuItem(22, "Mango Lassi", "beverages", 100, 5));
        items.add(new MenuItem(23, "Masala Chai", "beverages", 40, 3));
        items.add(new MenuItem(24, "Fresh Lemonade", "beverages", 80, 3));
        items.add(new MenuItem(25, "Iced Tea", "beverages", 60, 4));
        items.add(new MenuItem(26, "Buttermilk", "beverages", 50, 2));
    }

    public MenuItem getItemById(int id) {
        return items.stream()
            .filter(item -> item.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public List<MenuItem> getItemsByCategory(String category) {
        return items.stream()
            .filter(item -> item.getCategory().equalsIgnoreCase(category))
            .toList();
    }

    public List<MenuItem> getAllItems() {
        return new ArrayList<>(items);
    }

    public void displayAllItems() {
        System.out.println("\n" + "=".repeat(110));
        System.out.println("MENU - ALL ITEMS");
        System.out.println("=".repeat(110));
        System.out.printf("%-2s | %-30s | %-12s | %-10s | %s\n", "ID", "NAME", "CATEGORY", "PRICE", "PREP TIME");
        System.out.println("-".repeat(110));
        
        items.forEach(System.out::println);
        System.out.println("=".repeat(110));
    }

    public void displayItemsByCategory(String category) {
        List<MenuItem> categoryItems = getItemsByCategory(category);
        System.out.println("\n" + "=".repeat(110));
        System.out.println("MENU - " + category.toUpperCase());
        System.out.println("=".repeat(110));
        System.out.printf("%-2s | %-30s | %-12s | %-10s | %s\n", "ID", "NAME", "CATEGORY", "PRICE", "PREP TIME");
        System.out.println("-".repeat(110));
        
        categoryItems.forEach(System.out::println);
        System.out.println("=".repeat(110));
    }

    public List<String> getCategories() {
        return items.stream()
            .map(MenuItem::getCategory)
            .distinct()
            .sorted()
            .toList();
    }
}
