package com.orders;

class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return orderId + " - " + customerName + " - ₹" + totalPrice;
    }
}

public class SortOrders {

    public static void bubbleSort(Order[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].totalPrice > arr[j + 1].totalPrice) {
                    Order temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(Order[] arr, int low, int high) {
        double pivot = arr[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j].totalPrice < pivot) {
                i++;
                Order temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Order temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void printOrders(Order[] arr) {
        for (Order o : arr) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        Order[] orders = {
            new Order(1, "Ajay", 4500.50),
            new Order(2, "Meena", 1250.75),
            new Order(3, "Kiran", 7800.00),
            new Order(4, "Riya", 2999.99),
            new Order(5, "John", 999.99)
        };

        System.out.println("Sorted by Bubble Sort:");
        bubbleSort(orders);
        printOrders(orders);

        Order[] orders2 = {
            new Order(1, "Ajay", 4500.50),
            new Order(2, "Meena", 1250.75),
            new Order(3, "Kiran", 7800.00),
            new Order(4, "Riya", 2999.99),
            new Order(5, "John", 999.99)
        };

        System.out.println("\nSorted by Quick Sort:");
        quickSort(orders2, 0, orders2.length - 1);
        printOrders(orders2);
    }
}
