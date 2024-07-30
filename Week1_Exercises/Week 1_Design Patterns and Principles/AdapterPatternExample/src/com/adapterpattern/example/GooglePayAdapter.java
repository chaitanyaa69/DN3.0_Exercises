package com.adapterpattern.example;

public class GooglePayAdapter implements PaymentProcessor {
    private GooglePay gpay;

    public GooglePayAdapter(GooglePay gpay) {
        this.gpay = gpay;
    }

    @Override
    public void processPayment(double amount) {
        gpay.makePayment(amount);
    }
}
