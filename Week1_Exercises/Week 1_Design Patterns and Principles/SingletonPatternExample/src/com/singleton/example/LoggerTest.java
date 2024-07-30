package com.singleton.example;

public class LoggerTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log messages
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        // Check if both logger1 and logger2 are the same instance
        if (logger1 == logger2) {
            System.out.println("Log1 and Log2 are the same instance.");
        } else {
            System.out.println("Log1 and Log2 are different instances.");
        }
    }
}
