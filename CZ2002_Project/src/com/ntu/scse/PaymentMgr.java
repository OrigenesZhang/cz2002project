package com.ntu.scse;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class PaymentMgr {
    static ArrayList<Payment> paymentList;
    int numberOfPayment = 0;
    public PaymentMgr(ArrayList<Payment> paymentList) {
        this.paymentList = paymentList;
        this.numberOfPayment = paymentList.size();
        System.out.println(this.numberOfPayment+"Payment loaded");
    }

    public Payment addPayment( ArrayList<Integer> bills,int termOfPayment)
    throws InvalidInfoException{
        int newPaymentID = 0;
        if(checkGap() == false) {
            newPaymentID = numberOfPayment+1;
        }
        else {
            for(int i = 0; i <paymentList.size();i++) {
                if(paymentList.get(i).getPaymentID() != i+1){
                    newPaymentID = i+1;
                    break;
                }
            }
        }
        try {
            Payment newPayment = new Payment(newPaymentID,bills,termOfPayment);
            numberOfPayment++;
            paymentList.add(newPayment);
            System.out.println("Total number of bills: " + numberOfPayment);
            return newPayment;

        }catch (InvalidInfoException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateBillStatus(){

    };//After payment up payment status of bills

    public Payment searchPayment(int paymentID){
        for(Payment p:paymentList){
            if(p.getPaymentID() == paymentID)
                return p;
        }
        System.out.println("The payment is not found");
        return null;
    }

    public boolean paymentDone(boolean t){
        if(t) return true;
        else return false;
    }

    public void deletePayment(int paymentID){
        for(Payment p:paymentList){
            paymentList.remove(p);
            numberOfPayment--;
            System.out.println("Number of Payments" + numberOfPayment);
        }


    }
    public void saveToFile(String paymentFileName) {
        //numOfBill = billList.size();
        if (numberOfPayment == 0) { //Nothing to save
            System.out.println("No payment to save to file!");
        }
        else {
            try {
                FileOutputStream foStream = new FileOutputStream(paymentFileName);
                BufferedOutputStream boStream = new BufferedOutputStream(foStream);
                ObjectOutputStream doStream = new ObjectOutputStream(boStream);

                for (int i = 0 ; i < numberOfPayment ; i++) {
                    doStream.writeObject(paymentList.get(i)); //Write guest list into file
                }
                System.out.println(numberOfPayment + " Payment saved to " + paymentFileName);
                doStream.close();
            }
            catch (IOException e){
                System.out.println("[Payment] File IO Error!" + e.getMessage());
            }
        }
    }
    private boolean checkGap() { //Checks if any gap due to previously deleted bills
        return !(paymentList.get(paymentList.size()-1).getPaymentID() == numberOfPayment);
    }
}



