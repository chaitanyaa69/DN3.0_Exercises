package com.decoratorpattern.example;

public class DecoratorTest {
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        System.out.println("Sending notifications using Email:");
        emailNotifier.send("Hello via Email!");

        System.out.println("\nSending notifications using Email and SMS:");
        smsNotifier.send("Hello via Email and SMS!");

        System.out.println("\nSending notifications using Email, SMS, and Slack:");
        slackNotifier.send("Hello via Email, SMS, and Slack!");
    }
}
