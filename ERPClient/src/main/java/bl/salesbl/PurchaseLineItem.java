package bl.salesbl;

import java.util.ArrayList;

import bl.customerbl.CustomerController;
import bl.inventorybl.InventoryController;
import bl.userbl.UserController;
import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.userblservice.UserInfo;
import util.BillType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.PurchaseVO;

public class PurchaseLineItem {
	InventoryInfo inventoryInfo;
	CustomerInfo customerInfo;
	UserInfo userInfo;
	
	public PurchaseLineItem(){
		inventoryInfo=new InventoryController();
		customerInfo=new CustomerController();
		userInfo=new UserController();
	}
    
    public ResultMessage alterInventory(PurchaseVO vo){
		return null;
	}
	
	public ResultMessage alterCustomer(PurchaseVO vo){
		if(vo.type==BillType.PURCHASE){
			return customerInfo.raiseCustomerReceive(Integer.parseInt(vo.customerID), vo.sum);
		}else{
			return customerInfo.reduceCustomerReceive(Integer.parseInt(vo.customerID), vo.sum);
		}
	}
	
	public String getUserName() {
		String ID=userInfo.getCurrentUserID();
		return userInfo.getCurrentUserNameByID(ID);
	}

	public ArrayList<CustomerVO> getAllSupplier() {
		return customerInfo.getAllSupplier();
	}

	public ArrayList<String> getAllInventory() {
		return null;
	}

	public ArrayList<CustomerVO> getAllCustomer() {
		return customerInfo.getAllSeller();
	}

}
