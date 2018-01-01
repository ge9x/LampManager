package bl.salesbl;

import java.util.ArrayList;

import bl.customerbl.CustomerBLFactory;
import bl.customerbl.CustomerController;
import bl.inventorybl.InventoryBLFactory;
import bl.inventorybl.InventoryController;
import bl.promotionbl.PromotionBLFactory;
import bl.promotionbl.PromotionBargainController;
import bl.promotionbl.PromotionCustomerController;
import bl.promotionbl.PromotionTotalController;
import bl.userbl.UserBLFactory;
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
import util.UserLimits;
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
		inventoryInfo=InventoryBLFactory.getInfo();
		customerInfo=CustomerBLFactory.getInfo();
		promotionBargainInfo=PromotionBLFactory.getBargainInfo();
		promotionCustomerInfo=PromotionBLFactory.getCustomerInfo();
		promotionTotalInfo=PromotionBLFactory.getTotalInfo();
		userInfo=UserBLFactory.getInfo();
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
	
	public ArrayList<String> getAllInventory() {
		return inventoryInfo.getAllInventoryName();
	}
	
	public PromotionCustomerVO findPromotionCustomerByName(String name) {
		return promotionCustomerInfo.findPromotionByName(name);
	}
	
	public PromotionBargainVO findPromotionBargainByName(String name) {
		return promotionBargainInfo.findPromotionByName(name);
	}
	
	public PromotionTotalVO findPromotionTotalByName(String name) {
		return promotionTotalInfo.findPromotionByName(name);
	}
	
	public UserLimits getCurrentUserLimits() {
		return userInfo.findUserByID(userInfo.getCurrentUserID()).limit;
	}
	
}
