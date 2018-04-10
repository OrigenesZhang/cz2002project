package com.ntu.scse;

import static com.ntu.scse.RoomStatus.RESERVED;
import static com.ntu.scse.roomUpdateChoice.ROOMSTATUS;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class BillMgr {
//    Functional Requirement:
//    1. Given a Guest ID, read/write his/her current bills from file
//    2. If new Guest(While Check In), create a new file for him (And add the room bill)
	
	static ArrayList<Bill> billList;
	int numOfBill =0;
	
    public BillMgr(ArrayList<Bill> billList) { //Initialize
        this.billList = new ArrayList<>(billList);
        numOfBill = billList.size();
        System.out.println(numOfBill + " Bills loaded!");
    }
	
    public Bill addBill (
    		Menu item,
            double quantity,
            double discount,
            double taxRate
            ) throws InvalidInfoException {
    	int newBillNo =0;
    	
    	if (checkGap() == false) { //no gap, add bill at back
    		newBillNo = numOfBill + 1;
    	}
    	else { //add bill in between
    		for (int i = 0; i < billList.size(); i++) {
    			if (billList.get(i).getBillNo() != (i+1)) {
    				newBillNo = i+1;
    				break;
    			}
    		}
    	}
    	try {
	        Bill newBill = new Bill(item, newBillNo, quantity, discount, taxRate);
	        billList.add(newBill);
	        numOfBill++;
	        System.out.println("Total number of bills: " + numOfBill);
	        return newBill;
    	}
    	catch (InvalidInfoException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Bill searchBill(int billNo) {
    	Bill bill = null;
    	for (int i = 0; i < billList.size(); i++) {
			if (billList.get(i).getBillNo() == billNo) {
				bill = billList.get(i);
				return bill;
			}
		}
    	System.out.println("Bill number does not exist!");
    	return bill;
    }

    public void deleteBill (int billNo){
    	for (int i = 0; i < billList.size(); i++) {
			if (billList.get(i).getBillNo() == billNo) {
				billList.remove(i);
				numOfBill--;
				System.out.println("Total number of bills: " + numOfBill);
				return;
			}
		}
    	System.out.println("Bill number does not exist!");
    }
	
	public void saveToFile(String billFileName) { 
		//numOfBill = billList.size();
		if (numOfBill == 0) { //Nothing to save
    		System.out.println("No bills to save to file!");
    	}
    	else {
	    	try {
				FileOutputStream foStream = new FileOutputStream(billFileName);
				BufferedOutputStream boStream = new BufferedOutputStream(foStream);
				ObjectOutputStream doStream = new ObjectOutputStream(boStream);
				
				for (int i = 0 ; i < numOfBill ; i++) {
					doStream.writeObject(billList.get(i)); //Write guest list into file
				}
				System.out.println(numOfBill + " Bills saved to " + billFileName);
				doStream.close();
			}
			catch (IOException e){
				System.out.println("[Bill] File IO Error!" + e.getMessage());
			}
    	}
    }
	
    private boolean checkGap() { //Checks if any gap due to previously deleted bills
    	return !(billList.get(billList.size()-1).getBillNo() == numOfBill);
    }
}
