package com.ntu.scse;

import java.util.ArrayList;
public class BillMgr {
	
	static ArrayList<Bill> billList = null;
	private String[] bStatus = { "OPEN", "PAID" };

    public BillMgr(ArrayList<Bill> billList) {
    	if (billList == null) //Initialize
			this.billList = new ArrayList<>();
		else
			this.billList = new ArrayList<>(billList);

        System.out.println(this.billList.size() + " Bills loaded!");
    }
	
    public Bill addBill (
			int resvNo,
			int roomDays,
			String roomType,
			String roomNo,
			double roomRate,
			double discount
            ) {
    	int newBillNo = billList.size()+1;
    	try {
	        Bill newBill = new Bill(newBillNo, resvNo, roomDays, roomType, roomNo, roomRate, discount);
	        billList.add(newBill);
			//System.out.println("Bill no. " + newBillNo + " has been added!");
	        //System.out.println("Total number of bills: " + billList.size());
	        return newBill;
    	}
    	catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*
    public Bill searchBill(int billNo) {
    	Bill bill;
    	for (int i = 0; i < billList.size(); i++) {
			if (billList.get(i).getBillNo() == billNo) {
				bill = billList.get(i);
				return bill;
			}
		}
    	return null;
    }
    */
    public boolean addOrderToBill(int billNo, Order order){
    	try {
			billList.get(billNo - 1).addOrder(order);
			return true;
		}catch(IndexOutOfBoundsException e){
    		e.printStackTrace();
    		return false;
		}
	}

	public boolean removeOrderFromBill(int billNo, int orderNo){
		for (Bill b : billList){
			if (b.getBillNo() == billNo){
				return(b.removeOrder(orderNo));
			}
		}
		return false;
	}

	public void printBill(int resvNo){

		for (Bill b : billList){
			if (b.getResvNo() == resvNo){

				System.out.println("Printing bill no. " + b.getBillNo());
				b.printOrders();

				System.out.format("%-15s%-15s%-15s%-15s%-20s%-25s%-25s%-25s%-15s\n", "Bill No.", "Resv No.",
						"Room Type", "Room Rate", "No. of nights", "Discount", "Tax", "Room Price","Bill Status");

				System.out.format("%-15d%-15s%-15s%-15s%-20s%-25.4s%-25s%-25s%-15s\n", b.getBillNo(), b.getResvNo(),
						b.getRoomType(), b.getRoomRate(), b.getRoomDays(), (b.getDiscount()*100)+"%", (b.getTaxRate()*100)+"%", "$"+ (b.getRoomDays()*b.getRoomRate()),
						b.getBillStatus());

				System.out.println("\n\nTotal Due:");
				System.out.format("%-15.7s", "$"+ (b.getTotal() * (1.0+b.getTaxRate())));
				System.out.println();
				//SET TO PAID
				b.setBillStatus(1);
				return;
			}
		}
    	System.out.println("Bill for reservation not found!");
	}

	public int getBillNoFromRoomNo(String roomNo){
		for (Bill b : billList){
			if (b.getRoomNo().equals(roomNo)){
				return b.getBillNo();
			}
		}
    	return 0;
	}
/*
    public void deleteBill (int billNo){
    	for (int i = 0; i < billList.size(); i++) {
			if (billList.get(i).getBillNo() == billNo) {
				billList.remove(i);
				System.out.println("Total number of bills: " + billList.size());
				return;
			}
		}
    	System.out.println("Bill number does not exist!");
    }
	*/
	public ArrayList<Bill> saveToFile() {
		return billList;
    }

}
