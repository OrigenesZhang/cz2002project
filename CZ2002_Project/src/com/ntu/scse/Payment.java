package com.ntu.scse;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import static com.ntu.scse.BillMgr.billList;

public class Payment {
    //1. payment contain which bill (arraylist)
    //2. paid time
    //3. terms of payment
    public static final int Cash = 1, Credit = 2;
    private int paymentID;
    private int customerID;
    private ArrayList<Integer> billIDList;
    private LocalDateTime paidTime = LocalDateTime.now();
    private int termOfPayment;
    private double sum;

    public Payment(int paymentID,int customerID,ArrayList<Integer> billIDList,int termOfPayment )
    throws InvalidInfoException{
        this.paymentID = paymentID;
        this.billIDList = billIDList;
        this.termOfPayment = termOfPayment;
        this.customerID = customerID;
        this.sum = 0;
        for( Integer i : billIDList ){
            BillMgr n = new BillMgr(billList);
            this.sum += n.searchBill(i).getPrice();//Add payment status in bill
        }
    }

    //getters

    public int getPaymentID() {
        return paymentID;
    }

    public ArrayList<Integer> getBills() {
        return billIDList;
    }

    public LocalDateTime getPaidTime() {
        return paidTime;
    }

    public int getTermOfPayment() {
        return termOfPayment;
    }

    public double getSum() {
        return sum;
    }

    //setters

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public void setBills(ArrayList<Integer> billIDList) {
        this.billIDList = billIDList;
    }

    public void setTermOfPayment(int termOfPayment) {
        this.termOfPayment = termOfPayment;
    }

    public void setPaidTime(LocalDateTime paidTime) {
        this.paidTime = paidTime;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
