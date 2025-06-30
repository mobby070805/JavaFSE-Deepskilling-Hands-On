package com.example;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class BankAccountTest {

    private BankAccount account;

    // Setup: Runs before each test
    @Before
    public void setUp() {
        account = new BankAccount();
        account.deposit(1000);  // Initial balance
    }

    // Teardown: Runs after each test
    @After
    public void tearDown() {
        account = null;
    }

    @Test
    public void testDeposit() {
        // Arrange
        int depositAmount = 500;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals(1500, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        // Arrange
        int withdrawAmount = 300;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals(700, account.getBalance());
    }
}
