package bl.salesbl;

import java.util.ArrayList;

import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesBLService;
import util.BillType;
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

public class PurchaseController implements SalesBLService,PurchaseInfo{
	private Purchase purchase;
	
	public PurchaseController(){
		purchase=new Purchase();
	}

	@Override
	public ArrayList<String> getAllPurchaseDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getPurchaseIDByDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getPurchaseIDByType(BillType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getPurchaseIDByCustomerID(String customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getPurchaseIDBySalesman(String salesman) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getPurchaseIDByInventory(String inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage examine(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<PurchaseVO> getAllSubmittedPurchase() {
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
	public ResultMessage addPurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addGoodsItem(GoodsItemVO item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage addSales(SalesVO vo) {
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

	@Override
	public String getUserName() {
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

	

	
}
