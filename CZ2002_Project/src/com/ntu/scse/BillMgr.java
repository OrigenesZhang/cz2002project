package com.ntu.scse;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BillMgr {
//    Functional Requirement:
//    1. Given a Guest ID, read/write his/her current bills from file
//    2. If new Guest(While Check In), create a new file for him (And add the room bill)
	
	static ArrayList<Bill> billList;
	int numOfBill;
	
    public BillMgr(ArrayList<Bill> billList) { //Initialize
        this.billList = new ArrayList<>(billList);
        int size = this.billList.size();
        System.out.println("Number of bills: " + size); //FOR IO TESTING
    }
	
	
	public void saveToFile(String billFileName) { 
		billList.add(new Bill(10.0)); //TESTING FILE IO
		billList.add(new Bill(20.0)); //TESTING FILE IO
		billList.add(new Bill(30.0)); //TESTING FILE IO
		numOfBill = billList.size();
    	
    	try {
			FileOutputStream foStream = new FileOutputStream(billFileName);
			BufferedOutputStream boStream = new BufferedOutputStream(foStream);
			ObjectOutputStream doStream = new ObjectOutputStream(boStream);
			
			for (int i = 0 ; i < numOfBill ; i++) {
				doStream.writeObject(billList.get(i)); //Write guest list into file
			}
			doStream.close();
		}
		catch (IOException e){
			System.out.println("[Bill] File IO Error!" + e.getMessage());
		}
    }
}
