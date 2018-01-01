package bl.examinationbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import bl.financialbl.FinanceBLFactory;
import bl.financialbl.FinanceController;
import bl.inventorybl.Inventory;
import bl.inventorybl.InventoryBLFactory;
import bl.inventorybl.InventoryController;
import bl.salesbl.PurchaseController;
import bl.salesbl.Sales;
import bl.salesbl.SalesBLFactory;
import bl.salesbl.SalesController;
import blservice.financeblservice.FinanceInfo;
import blservice.inventoryblservice.InventoryInfo;
import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesInfo;
import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;
import rmi.ExaminationRemoteHelper;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillVO;
import vo.BillVO;
import vo.CashBillVO;
import vo.InventoryBillVO;
import vo.PurchaseVO;
import vo.SalesVO;

public class Examination {

	private ExaminationDataService examinationDataService;
	private ArrayList<BillPO> billPOs;
	private FinanceInfo financeInfo;
	private SalesInfo salesInfo;
	private PurchaseInfo purchaseInfo;
	private InventoryInfo inventoryInfo;
	
	public Examination(){
		examinationDataService = ExaminationRemoteHelper.getInstance().getExaminationDataService();
		financeInfo = FinanceBLFactory.getInfo();
		salesInfo = SalesBLFactory.getSalesInfo();
		purchaseInfo = SalesBLFactory.getPurchaseInfo();
		inventoryInfo = InventoryBLFactory.getInfo();
	}
	

	public ArrayList<BillVO> show(){
		ArrayList<BillVO> bills = new ArrayList<>();
		bills.addAll(inventoryInfo.getAllSubmittedInventoryBill());
		bills.addAll(financeInfo.getAllSubmittedCashBills());
		bills.addAll(financeInfo.getAllSubmittedPayments());
		bills.addAll(financeInfo.getAllSubmittedReceipts());
		bills.addAll(salesInfo.getAllSubmittedSales());
		bills.addAll(purchaseInfo.getAllSubmittedPurchase());
		Collections.sort(bills, new Comparator<BillVO>(){

			@Override
			public int compare(BillVO o1, BillVO o2) {
				SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date date1 = sd.parse(o1.date);
					Date date2 = sd.parse(o2.date);
					return date1.compareTo(date2);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}
			
		});
		return bills;
	}
	
	public ResultMessage modifyReceipt(BillVO bill){
		if(bill.type==BillType.CASH){
			CashBillVO cashBill = (CashBillVO) bill;
			financeInfo.examine(cashBill);
		}
		else if(bill.type==BillType.PAYMENT||bill.type==BillType.RECEIPT){
			AccountBillVO accountBill = (AccountBillVO) bill;
			financeInfo.examine(accountBill);
		}
		else if(bill.type==BillType.PURCHASE||bill.type==BillType.RETURN){
			PurchaseVO purchaseBill = (PurchaseVO) bill;
			purchaseInfo.examine(purchaseBill);
		}
		else if(bill.type==BillType.SALES||bill.type==BillType.SALESRETURN){
			SalesVO salesBill = (SalesVO) bill;
			salesInfo.examine(salesBill);
		}
		else if(bill.type==BillType.LOSS||bill.type==BillType.OVERFLOW){
			InventoryBillVO inventoryBillVO = (InventoryBillVO) bill;
			inventoryInfo.examine(inventoryBillVO);
		}
		return ResultMessage.SUCCESS;
	}
	
	public ResultMessage approveReceipt(BillVO bill){
		bill.state = BillState.PASS;
		return modifyReceipt(bill);
	}
	
	public ResultMessage refuseReceipt(BillVO bill){
		bill.state = BillState.FAILED;
		return modifyReceipt(bill);
	}
}
