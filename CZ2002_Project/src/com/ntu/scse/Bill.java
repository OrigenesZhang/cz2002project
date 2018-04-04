package com.ntu.scse;


public class Bill {
    private double price, quantity, discount, taxRate, total;
    private Menu item;

    public Bill(Menu item,
                double quantity,
                double discount,
                double taxRate
                ) {

        this.item = item;
        this.quantity = quantity;
        this.discount = discount;
        this.taxRate = taxRate;

//        this.price = this.item.price;  // YET TO IMPLEMENT MENU
//        this.total = this.price * this.quantity * this.discount * (1+this.taxRate);  // YET TO IMPLEMENT MENU

    }
}
