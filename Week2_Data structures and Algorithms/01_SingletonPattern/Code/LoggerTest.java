package com.singleton;

public class LoggerTest {
    public static void main(String[] args) {
        Logger loggerA = Logger.getInstance();
        Logger loggerB = Logger.getInstance();

        loggerA.log("Logging started.");
        loggerB.log("Another log message.");

        if (loggerA == loggerB) {
            System.out.println("Same Logger instance is being used.");
        } else {
            System.out.println("Different instances found.");
        }
    }
}

