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

	public ResultMessage addPurchase(PurchaseVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addGoodsItem(GoodsItemVO item) {
		// TODO Auto-generated method stub
		return ResultMessage.NULL;
	}

	public ResultMessage addSales(SalesVO vo) {
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
	public ArrayList<Integer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
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
	public ArrayList<String> getSalesOrderIDByDate(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesOrderIDByGoodsName(String goodsName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesOrderIDByCustomerID(String customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesOrderIDBySalesman(String salesman) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesOrderIDByInventory(String inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getSalesIDByDate(Date startDate, Date endDate) {
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
	public Date getDateByID(String ID) {
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
	
}
