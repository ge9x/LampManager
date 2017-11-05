package bl.salesbl;

import java.util.ArrayList;

import blservice.salesblservice.SalesBLService;
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

public class PurchaseController implements SalesBLService{
	private Purchase purchase;
	
	public PurchaseController(){
		purchase=new Purchase();
	}

	@Override
	public String getnewPurchaseID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getnewReturnID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getnewSalesID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getnewSalesReturnID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PromotionBargainVO> showBargains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PromotionTotalVO> getFitPromotionTotal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PurchaseVO addPurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGoodsItem(GoodsItemVO item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SalesVO addSales(SalesVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage submitPurchase(PurchaseVO pur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage submitSales(SalesVO sal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage saveSales(SalesVO bill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage savePurchase(PurchaseVO bill) {
		// TODO Auto-generated method stub
		return null;
	}
}
