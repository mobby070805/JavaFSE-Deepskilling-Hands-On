package com.inventory;

import java.util.HashMap;

public class InventoryManager {
    private HashMap<Integer, Product> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    public void addProduct(Product p) {
        inventory.put(p.getProductId(), p);
        System.out.println("Product added.");
    }

    public void updateProduct(int productId, int quantity, double price) {
        if (inventory.containsKey(productId)) {
            Product p = inventory.get(productId);
            p.setQuantity(quantity);
            p.setPrice(price);
            System.out.println("Product updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(int productId) {
        if (inventory.remove(productId) != null)
            System.out.println("Product deleted.");
        else
            System.out.println("Product not found.");
    }

    public void displayInventory() {
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
    }
}
