package blstubdriver;

import java.util.ArrayList;
import java.util.Date;

import blservice.salesblservice.SalesBLService;
import util.PromotionType;
import util.ResultMessage;
import vo.GoodsItemVO;
import vo.GoodsVO;
import util.Level;
import vo.PromotionBargainVO;
import vo.PromotionCustomerVO;
import vo.PromotionTotalVO;
import vo.PurchaseVO;
import vo.SalesVO;

public class SalesBLService_Stub implements SalesBLService{
	String purchaseID;
	String returnID;
	String salesID;
	String salesReturnID; 
	Date startDate;
	Date endDate;

	

	public SalesBLService_Stub(String purchaseID, String returnID, String salesID, String salesReturnID, Date startDate,
			Date endDate) {
		super();
		this.purchaseID = purchaseID;
		this.returnID = returnID;
		this.salesID = salesID;
		this.salesReturnID = salesReturnID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getnewPurchaseID() {
		return purchaseID;
	}

	public String getnewReturnID() {
		return returnID;
	}

	public String getnewSalesID() {
		return salesID;
	}

	public String getnewSalesReturnID() {
		return salesReturnID;
	}

	public ArrayList<PromotionBargainVO> showBargains() {
		ArrayList<PromotionBargainVO> getBargains=new ArrayList<PromotionBargainVO>();
		getBargains.add(new PromotionBargainVO(returnID, 5000, 1000, startDate , endDate , new ArrayList<GoodsVO>()));
		return getBargains;
	}

	public ArrayList<PromotionCustomerVO> getFitPromotionCustomer() {
		ArrayList<PromotionCustomerVO> getCustomers=new ArrayList<PromotionCustomerVO>();
		getCustomers.add(new PromotionCustomerVO(salesID, startDate, endDate, 500.0, 300.0, new ArrayList<GoodsVO>(), util.Level.LEVEL_ONE));
		return getCustomers;
	} 

	public ArrayList<PromotionTotalVO> getFitPromotionTotal() {
		ArrayList<PromotionTotalVO> getTotal=new ArrayList<PromotionTotalVO>();
		getTotal.add(new PromotionTotalVO(purchaseID, startDate, endDate, 455.0, new ArrayList<GoodsVO>(), 700.0));
		return getTotal;
	}

	public PurchaseVO addPurchase(PurchaseVO vo) {
		System.out.println("add purchaseBill success");
		return vo;
	}

	public void addGoodsItem(GoodsItemVO item) {
		System.out.println("add goodsItem success");
	}

	public SalesVO addSales(SalesVO vo) {
		System.out.println("add salesBill success");
		return vo;
	}

	public ResultMessage submitPurchase(PurchaseVO pur) {
		if(pur.ID.equals("JHD-yyyyMMdd-00001")){
			System.out.println("Submit purchaseBill success");
			return ResultMessage.SUCCESS;
		}else{
			System.out.println("Submit purchaseBill failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage submitSales(SalesVO sal) {
		if(sal.ID.equals("XSD-yyyyMMdd-00001")){
			System.out.println("Submit salesBill success");
			return ResultMessage.SUCCESS;
		}else{
			System.out.println("Submit salesBill failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage saveSales(SalesVO bill) {
		if(bill.ID.equals("XSD-yyyyMMdd-00001")){
			System.out.println("Save salesBill success");
			return ResultMessage.SUCCESS;
		}else{
			System.out.println("Save salesBill failed");
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage savePurchase(PurchaseVO bill) {
		if(bill.ID.equals("JHD-yyyyMMdd-00001")){
			System.out.println("Save purchaseBill success");
			return ResultMessage.SUCCESS;
		}else{
			System.out.println("Save purchaseBill failed");
			return ResultMessage.FAILED;
		}
	}
	
}
