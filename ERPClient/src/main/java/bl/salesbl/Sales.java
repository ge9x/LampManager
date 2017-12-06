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
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public class Sales {
	private SalesVO vo;
	private SalesDataService salesDataService;
	
	public Sales(){
		
	}
	
	
	public SalesVO findSlaesByID(String ID) {
		return null;
	}
	
	public SalesVO findSalesByState(BillState state) {
		return null;
	}
	
	public ResultMessage addSales(SalesVO vo){
		return null;
	}
	
	public ResultMessage updateSales(SalesVO vo){
		return null;
	}
	
	public ResultMessage deleteSales(String ID) {
		return null;
	}
	
	public void init(){
		
	}
	
	public ResultMessage submitSales(SalesVO vo){
		return null;
	}
	
}
