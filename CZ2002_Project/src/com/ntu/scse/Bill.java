package com.ntu.scse;

import java.io.Serializable;

public class Bill implements Serializable {
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
    
    public Bill (double price) { //TESTING CONSTRUCTOR FOR IO TESTING
    	this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getTotal() {
        return total;
    }

    public Menu getItem() {
        return item;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public void setItem(Menu item) {
        this.item = item;
    }
}
