package bl.salesbl;

import java.util.ArrayList;

import bl.customerbl.CustomerController;
import bl.inventorybl.InventoryController;
import bl.promotionbl.PromotionBargainController;
import bl.promotionbl.PromotionCustomerController;
import bl.promotionbl.PromotionTotalController;
import bl.userbl.UserController;
import blservice.customerblservice.CustomerInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.promotionblservice.promotionTotal.PromotionTotalInfo;
import blservice.promotionblservice.promotionbargain.PromotionBargainInfo;
import blservice.promotionblservice.promotioncustomer.PromotionCustomerInfo;
import blservice.userblservice.UserInfo;
import util.BillType;
import util.Level;
import util.ResultMessage;
import vo.CustomerVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.SalesVO;

public class SalesLineItem {
   
	InventoryInfo inventoryInfo;
	CustomerInfo customerInfo;
	PromotionBargainInfo promotionBargainInfo;
	PromotionCustomerInfo promotionCustomerInfo;
	PromotionTotalInfo promotionTotalInfo;
	UserInfo userInfo;
	
	public SalesLineItem(){
		inventoryInfo=new InventoryController();
		customerInfo=new CustomerController();
		promotionBargainInfo=new PromotionBargainController();
		promotionCustomerInfo=new PromotionCustomerController();
		promotionTotalInfo=new PromotionTotalController();
		userInfo=new UserController();
	}

	
	public ArrayList<PromotionBargainVO> showBargains() {
		return promotionBargainInfo.getFitPromotionBargain();
	}

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer(Level level) {
		return promotionCustomerInfo.getFitPromotionCustomer(level);
	}

	public ArrayList<PromotionTotalVO> getFitPromotionTotal(double total) {
		return promotionTotalInfo.getFitPromotionTotal(total);
	}
	
	public String getUserName() {
		String ID=userInfo.getCurrentUserID();
		return userInfo.getCurrentUserNameByID(ID);
	}
	
	public ArrayList<CustomerVO> getAllSupplier() {
		return customerInfo.getAllSupplier();
	}
	
	public ArrayList<CustomerVO> getAllCustomer() {
		return customerInfo.getAllSeller();
	}
    
    public ResultMessage alterInventory(SalesVO vo){
		return null;
	}
	
	public ResultMessage alterCustomer(SalesVO vo){
		if(vo.type==BillType.SALES){
			return customerInfo.raiseCustomerPay(Integer.parseInt(vo.customerID), vo.afterSum);
		}else{
			return customerInfo.reduceCustomerPay(Integer.parseInt(vo.customerID), vo.afterSum);
		}
	}
}
