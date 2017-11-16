package bl.salesbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.salesdataservice.SalesDataService;
import po.PurchasePO;
import po.SalesPO;
import util.BillState;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Purchase {
	private PurchaseVO vo;
	private PurchaseList purchaseList;
	
	private SalesDataService salesDataService;
	
	public Purchase(){
		
	}
	
	public PurchaseVO findPurchaseByID(String ID) {
		return null;
	}
	
	public PurchaseVO findPurchaseByState(BillState state) {
		return null;
	}
	
	public ResultMessage addPurchase(PurchaseVO vo) {
		return null;
	}
	
	public ResultMessage updatePurchase(PurchaseVO vo) {
		return null;
	}
		
	public ResultMessage deletePurchase(String ID) {
		return null;
	}
	
	public void init() {
		
	}
	
	public ResultMessage submitPurchase(PurchaseVO vo){
		return null;
	}
	
	
}
