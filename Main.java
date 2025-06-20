package com.inventory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add\n2. Update\n3. Delete\n4. View\n5. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: "); int id = sc.nextInt();
                    System.out.print("Name: "); sc.nextLine(); String name = sc.nextLine();
                    System.out.print("Qty: "); int qty = sc.nextInt();
                    System.out.print("Price: "); double price = sc.nextDouble();
                    Product p = new Product(id, name, qty, price);
                    manager.addProduct(p);
                    break;
                case 2:
                    System.out.print("ID: "); id = sc.nextInt();
                    System.out.print("New Qty: "); qty = sc.nextInt();
                    System.out.print("New Price: "); price = sc.nextDouble();
                    manager.updateProduct(id, qty, price);
                    break;
                case 3:
                    System.out.print("ID: "); id = sc.nextInt();
                    manager.deleteProduct(id);
                    break;
                case 4:
                    manager.displayInventory();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
