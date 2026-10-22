package com.restaurant;

import java.util.*;

/**
 * Main Restaurant Ordering System Application
 * Provides an interactive console-based interface for customers to order food
 */
public class RestaurantOrderingSystem {
    private Menu menu;
    private Order currentOrder;
    private Scanner scanner;

    public RestaurantOrderingSystem() {
        this.menu = new Menu();
        this.currentOrder = new Order();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        displayWelcome();

        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    browseMenu();
                    break;
                case "2":
                    addItemToOrder();
                    break;
                case "3":
                    viewCurrentOrder();
                    break;
                case "4":
                    removeItemFromOrder();
                    break;
                case "5":
                    generateBill();
                    break;
                case "6":
                    newOrder();
                    break;
                case "7":
                    displayExit();
                    running = false;
                    break;
                default:
                    System.out.println("\n❌ Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void displayWelcome() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("🍔 WELCOME TO RESTAURANT ORDERING SYSTEM 🍔");
        System.out.println("=".repeat(70));
        System.out.println("Select your favorite items and generate your bill!\n");
    }

    private void displayMainMenu() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("MAIN MENU");
        System.out.println("-".repeat(70));
        System.out.println("1. Browse Menu");
        System.out.println("2. Add Item to Order");
        System.out.println("3. View Current Order");
        System.out.println("4. Remove Item from Order");
        System.out.println("5. Generate Bill");
        System.out.println("6. New Order (Clear Current Order)");
        System.out.println("7. Exit");
        System.out.print("\nEnter your choice (1-7): ");
    }

    private void browseMenu() {
        while (true) {
            System.out.println("\n" + "-".repeat(70));
            System.out.println("BROWSE MENU");
            System.out.println("-".repeat(70));
            System.out.println("1. View All Items");
            System.out.println("2. View by Category");
            System.out.println("3. Back to Main Menu");
            System.out.print("\nEnter your choice (1-3): ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    menu.displayAllItems();
                    break;
                case "2":
                    browseByCategory();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }

    private void browseByCategory() {
        List<String> categories = menu.getCategories();
        
        System.out.println("\nAvailable Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).toUpperCase());
        }
        System.out.print("\nSelect category (enter number): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice >= 1 && choice <= categories.size()) {
                menu.displayItemsByCategory(categories.get(choice - 1));
            } else {
                System.out.println("❌ Invalid category number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid number.");
        }
    }

    private void addItemToOrder() {
        if (menu.getAllItems().isEmpty()) {
            System.out.println("❌ Menu is empty.");
            return;
        }

        menu.displayAllItems();

        System.out.print("\nEnter Item ID to add: ");
        try {
            int itemId = Integer.parseInt(scanner.nextLine().trim());
            MenuItem item = menu.getItemById(itemId);

            if (item == null) {
                System.out.println("❌ Item not found with ID: " + itemId);
                return;
            }

            System.out.print("Enter quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());

            if (quantity <= 0) {
                System.out.println("❌ Quantity must be greater than 0.");
                return;
            }

            currentOrder.addItem(item, quantity);
            System.out.printf("✅ Added %d x %s to order.%n", quantity, item.getName());
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter valid numbers.");
        }
    }

    private void viewCurrentOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("\n⚠️  Your order is empty.");
            return;
        }

        System.out.println(currentOrder);
        System.out.println("ORDER DETAILS:");
        System.out.printf("Subtotal: ₹%.2f%n", currentOrder.getSubtotal());
        System.out.printf("GST (5%%): ₹%.2f%n", currentOrder.getGST());
        System.out.printf("Grand Total: ₹%.2f%n", currentOrder.getGrandTotal());
        System.out.printf("Est. Preparation Time: %d minutes%n", currentOrder.getTotalPreparationTime());
        System.out.println("=".repeat(100));
    }

    private void removeItemFromOrder() {
        if (currentOrder.isEmpty()) {
            System.out.println("\n⚠️  Your order is empty.");
            return;
        }

        viewCurrentOrder();

        System.out.print("\nEnter Item ID to remove: ");
        try {
            int itemId = Integer.parseInt(scanner.nextLine().trim());

            if (currentOrder.getItem(itemId) != null) {
                currentOrder.removeItem(itemId);
                System.out.println("✅ Item removed from order.");
            } else {
                System.out.println("❌ Item not found in your order.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Please enter a valid item ID.");
        }
    }

    private void generateBill() {
        if (currentOrder.isEmpty()) {
            System.out.println("\n⚠️  Your order is empty. Please add items first.");
            return;
        }

        Receipt receipt = new Receipt(currentOrder);
        receipt.displayBill();

        System.out.print("Do you want to start a new order? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        if (response.equals("yes") || response.equals("y")) {
            newOrder();
        }
    }

    private void newOrder() {
        System.out.println("\n✅ Starting a new order. Previous order cleared.");
        currentOrder.clear();
    }

    private void displayExit() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("Thank you for using Restaurant Ordering System!");
        System.out.println("Visit us again soon! 🙏");
        System.out.println("=".repeat(70) + "\n");
    }

    public static void main(String[] args) {
        RestaurantOrderingSystem app = new RestaurantOrderingSystem();
        app.start();
    }
}
