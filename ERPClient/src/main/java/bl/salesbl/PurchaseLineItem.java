package bl.salesbl;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;

import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.promotionblservice.PromotionInfo;
import util.ResultMessage;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;

public class PurchaseLineItem {
	private PurchaseVO purchase;
	
	private InventoryInfo inventoryInfo;
	private CustomerInfo customerInfo;
	private PromotionInfo promotionInfo;
	
	public PurchaseLineItem(){
		
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
		return null;
	}
	
}
