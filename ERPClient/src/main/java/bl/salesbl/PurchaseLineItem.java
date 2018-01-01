package bl.salesbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import bl.customerbl.CustomerBLFactory;
import bl.customerbl.CustomerController;
import bl.inventorybl.InventoryBLFactory;
import bl.inventorybl.InventoryController;
import bl.userbl.UserBLFactory;
import bl.userbl.UserController;
import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.userblservice.UserInfo;
import dataservice.salesdataservice.SalesDataService;
import rmi.SalesRemoteHelper;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.PurchaseVO;

public class PurchaseLineItem {
	InventoryInfo inventoryInfo;
	CustomerInfo customerInfo;
	UserInfo userInfo;
	
	public PurchaseLineItem(){
		inventoryInfo=InventoryBLFactory.getInfo();
		customerInfo=CustomerBLFactory.getInfo();
		userInfo=UserBLFactory.getInfo();
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
		return inventoryInfo.getAllInventoryName();
	}

	public ArrayList<CustomerVO> getAllCustomer() {
		return customerInfo.getAllSeller();
	}
	
}
