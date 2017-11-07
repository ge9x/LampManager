package bl.salesbl;

import java.util.ArrayList;

import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.SalesVO;

public class SalesLineItem {
    private SalesVO sales;
	
	private InventoryInfo inventoryInfo;
	private CustomerInfo customerInfo;
	private PromotionInfo promotionInfo;
	
	public SalesLineItem(){
		
	}
	
	public String getNewSalesID(){
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
}
