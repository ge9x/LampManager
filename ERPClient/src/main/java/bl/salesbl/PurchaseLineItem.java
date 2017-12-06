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
import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.promotionblservice.PromotionInfo;
import po.GoodsItemPO;
import po.PurchasePO;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;

public class PurchaseLineItem {
	private PurchaseVO purchase;
	
	InventoryInfo inventoryInfo;
	CustomerInfo customerInfo;
	PromotionInfo promotionInfo;
	
	public PurchaseLineItem(){
		inventoryInfo=new InventoryController();
		customerInfo=new CustomerController();

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
