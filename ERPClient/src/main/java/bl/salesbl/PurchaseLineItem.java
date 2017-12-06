package bl.salesbl;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXPopup.PopupHPosition;

import bl.customerbl.CustomerController;
import bl.inventorybl.InventoryController;
import bl.promotionbl.PromotionBargainController;
import bl.promotionbl.PromotionCustomerController;
import bl.promotionbl.PromotionTotalController;
import bl.userbl.UserController;
import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.promotionblservice.PromotionInfo;
import blservice.userblservice.UserInfo;
import dataservice.salesdataservice.SalesDataService;
import po.GoodsItemPO;
import po.PurchasePO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;

public class PurchaseLineItem {
	
	InventoryInfo inventoryInfo;
	CustomerInfo customerInfo;
	PromotionInfo promotionInfo;
	UserInfo userInfo;
	
	public PurchaseLineItem(){
		inventoryInfo=new InventoryController();
		customerInfo=new CustomerController();

		userInfo=new UserController();
	}
	
	public String getNewPurchaseID(){
		return null;
	}
	
	public ArrayList <PromotionBargainVO> showBargains(){
	    return null;
	}
		 
    public ArrayList <PromotionCustomerVO> getFitPromotionCustomer(){
		return null;
    }
		  
    public ArrayList <PromotionTotalVO> getFitPromotionTotal(){
		return null;
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
	
	public String getUserID() {
		return userInfo.getCurrentUserID();
	}

	public ArrayList<Integer> getAllSupplier() {
		return customerInfo.getAllSupplier();
	}

	public ArrayList<String> getAllInventory() {
		return null;
	}

	public ArrayList<Integer> getAllCustomer() {
		return customerInfo.getAllSeller();
	}

}
