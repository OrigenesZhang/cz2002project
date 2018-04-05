package com.ntu.scse;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BillMgr {
//    Functional Requirement:
//    1. Given a Guest ID, read/write his/her current bills from file
//    2. If new Guest(While Check In), create a new file for him (And add the room bill)
	
	
	public void saveToFile(String billFileName) { //NOT DONE
    	try {
			FileOutputStream foStream = new FileOutputStream(billFileName);
			BufferedOutputStream boStream = new BufferedOutputStream(foStream);
			ObjectOutputStream doStream = new ObjectOutputStream(boStream);
			
			//WRITE BILL DETAILS TO FILE
			doStream.close();
		}
		catch (IOException e){
			System.out.println("[Bill] File IO Error!" + e.getMessage());
		}
    }
}
