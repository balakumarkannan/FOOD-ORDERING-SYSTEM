package com.restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Generates and displays the bill/receipt for a customer order
 */
public class Receipt {
    private Order order;
    private LocalDateTime generatedTime;

    public Receipt(Order order) {
        this.order = order;
        this.generatedTime = LocalDateTime.now();
    }

    public void displayBill() {
        if (order.isEmpty()) {
            System.out.println("\nOrder is empty. Cannot generate bill.");
            return;
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("🧾 RESTAURANT BILL / RECEIPT");
        System.out.println("=".repeat(70));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("Generated on: " + generatedTime.format(formatter));
        
        System.out.println("-".repeat(70));
        System.out.println("ITEMS ORDERED:");
        System.out.println("-".repeat(70));

        order.getItems().forEach(item -> {
            System.out.printf("%-30s | Qty: %2d | ₹%-8.2f x %d = ₹%-10.2f%n",
                item.getMenuItem().getName(),
                item.getQuantity(),
                item.getMenuItem().getPrice(),
                item.getQuantity(),
                item.getTotalPrice());
        });

        System.out.println("-".repeat(70));
        System.out.printf("%-50s ₹%-10.2f%n", "SUBTOTAL:", order.getSubtotal());
        System.out.printf("%-50s ₹%-10.2f%n", "GST (5%):", order.getGST());
        System.out.println("=".repeat(70));
        System.out.printf("%-50s ₹%-10.2f%n", "GRAND TOTAL:", order.getGrandTotal());
        System.out.println("=".repeat(70));
        
        System.out.printf("⏱️  Estimated Preparation Time: %d minutes%n", order.getTotalPreparationTime());
        System.out.println("=".repeat(70));
        System.out.println("Thank you for your order! Please collect your bill.\n");
    }

    public void printInvoice() {
        StringBuilder invoice = new StringBuilder();
        
        invoice.append("\n").append("=".repeat(70)).append("\n");
        invoice.append("🧾 RESTAURANT BILL / RECEIPT\n");
        invoice.append("=".repeat(70)).append("\n");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        invoice.append("Generated on: ").append(generatedTime.format(formatter)).append("\n");
        
        invoice.append("-".repeat(70)).append("\n");
        invoice.append("ITEMS ORDERED:\n");
        invoice.append("-".repeat(70)).append("\n");

        order.getItems().forEach(item -> {
            invoice.append(String.format("%-30s | Qty: %2d | ₹%-8.2f x %d = ₹%-10.2f%n",
                item.getMenuItem().getName(),
                item.getQuantity(),
                item.getMenuItem().getPrice(),
                item.getQuantity(),
                item.getTotalPrice()));
        });

        invoice.append("-".repeat(70)).append("\n");
        invoice.append(String.format("%-50s ₹%-10.2f%n", "SUBTOTAL:", order.getSubtotal()));
        invoice.append(String.format("%-50s ₹%-10.2f%n", "GST (5%):", order.getGST()));
        invoice.append("=".repeat(70)).append("\n");
        invoice.append(String.format("%-50s ₹%-10.2f%n", "GRAND TOTAL:", order.getGrandTotal()));
        invoice.append("=".repeat(70)).append("\n");
        invoice.append(String.format("⏱️  Estimated Preparation Time: %d minutes%n", order.getTotalPreparationTime()));
        invoice.append("=".repeat(70)).append("\n");
        invoice.append("Thank you for your order! Please collect your bill.\n");

        System.out.println(invoice.toString());
    }

    public double getSubtotal() {
        return order.getSubtotal();
    }

    public double getGST() {
        return order.getGST();
    }

    public double getGrandTotal() {
        return order.getGrandTotal();
    }

    public int getPreparationTime() {
        return order.getTotalPreparationTime();
    }
}
