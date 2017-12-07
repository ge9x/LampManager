package bl.salesbl;

import java.sql.Date;
import java.util.ArrayList;

import blservice.salesblservice.SalesBLService;
import blservice.salesblservice.SalesInfo;
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

public class SalesController implements SalesBLService,SalesInfo{

	private Sales sales;
	
	public SalesController(){
		sales=new Sales();
	}

	@Override
	public ArrayList<String> getAllSalesDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllGoodsName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesman() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllCustomerID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SalesVO> getAllSalesOrder() {
		return sales.getAllSalesOrder();
	}

	@Override
	public ArrayList<String> getSalesIDByDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesIDByType(BillType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesIDByCustomerID(String customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesIDBySalesman(String salesman) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesIDByInventory(String invenory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDateByID(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GoodsItemVO> getGoodsItemByID(String ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAllowance(String ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSumByID(String ID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ResultMessage examine(SalesVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SalesVO> getAllSubmittedSales() {
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
	public ArrayList<Integer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
