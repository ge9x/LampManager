package bl.salesbl;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXPopup.PopupHPosition;

import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.promotionblservice.PromotionInfo;
import po.GoodsItemPO;
import po.PurchasePO;
import util.BillState;
import util.BillType;
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
	
	public static PurchasePO voTopo(PurchaseVO vo){
		return new PurchasePO(vo.type,vo.state,vo.supplier,Integer.parseInt(vo.customerID),vo.inventory,vo.user,(List)vo.goodsItemList,vo.remarks,vo.date,1);
	}
	
	public static PurchasePO poTovo(PurchasePO po){
		return null;
		//return new PurchaseVO(po.getType(), po, ID, supplier, customerID, inventory, user, goodsItemList, remarks, endDate)
	}
}
