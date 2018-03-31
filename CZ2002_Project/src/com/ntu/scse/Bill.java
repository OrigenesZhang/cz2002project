package com.ntu.scse;

public class Bill {
    private double price, discount, tax, total;
    private String[] item;

    public Bill(double price, double discount, double tax, double total, String[] item) {
        this.price = price;
        this.discount = discount;
        this.tax = tax;
        this.total = total;
        this.item = item;
    }
}
