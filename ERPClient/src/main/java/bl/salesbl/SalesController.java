package bl.salesbl;

import java.util.ArrayList;

import blservice.salesblservice.SalesBLService;
import util.ResultMessage;
import vo.CustomerVO;
import vo.GoodsItemVO;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

/**
 * Created by zlk on 2017/11/5
 */

public class SalesController implements SalesBLService{

	private Sales sales;
	
	public SalesController(){
		sales=new Sales();
	}
	
	public String getnewPurchaseID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getnewReturnID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getnewSalesID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getnewSalesReturnID() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PromotionBargainVO> showBargains() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PromotionTotalVO> getFitPromotionTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	public PurchaseVO addPurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addGoodsItem(GoodsItemVO item) {
		// TODO Auto-generated method stub
		
	}

	public SalesVO addSales(SalesVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage submitPurchase(PurchaseVO pur) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage submitSales(SalesVO sal) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage saveSales(SalesVO bill) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage savePurchase(PurchaseVO bill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> getAllSupplier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CustomerVO> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
