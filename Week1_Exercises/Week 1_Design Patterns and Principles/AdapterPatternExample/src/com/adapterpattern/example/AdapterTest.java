package com.adapterpattern.example;

public class AdapterTest {
    public static void main(String[] args) {
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPal());
        payPalProcessor.processPayment(50.0);

        PaymentProcessor stripeProcessor = new GooglePayAdapter(new GooglePay());
        stripeProcessor.processPayment(75.0);
    }
}
