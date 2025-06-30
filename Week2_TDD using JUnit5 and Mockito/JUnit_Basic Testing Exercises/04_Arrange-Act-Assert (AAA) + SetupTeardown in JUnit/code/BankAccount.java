package com.example;

public class BankAccount {

    private int balance;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount;
        }
    }

    public int getBalance() {
        return balance;
    }
}

