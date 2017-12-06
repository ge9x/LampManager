package bl.examinationbl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import bl.financialbl.FinanceController;
import bl.salesbl.PurchaseController;
import bl.salesbl.SalesController;
import blservice.financeblservice.FinanceInfo;
import blservice.salesblservice.PurchaseInfo;
import blservice.salesblservice.SalesInfo;
import dataservice.examinationdataservice.ExaminationDataService;
import po.BillPO;
import po.CashBillPO;
import rmi.ExaminationRemoteHelper;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.BillVO;
import vo.CashBillItemVO;
import vo.CashBillVO;

public class Examination {

	private ExaminationDataService examinationDataService;
	ArrayList<BillPO> billPOs;
	FinanceInfo financeInfo;
	SalesInfo salesInfo;
	PurchaseInfo purchaseInfo;
	
	public Examination(){
		examinationDataService = ExaminationRemoteHelper.getInstance().getExaminationDataService();
		financeInfo = new FinanceController();
		salesInfo = new SalesController();
		purchaseInfo = new PurchaseController();
	}
	

	public ArrayList<BillVO> show(){
		ArrayList<BillVO> bills = new ArrayList<>();
		bills.addAll(financeInfo.getAllSubmittedCashBills());
		bills.addAll(financeInfo.getAllSubmittedPayments());
		bills.addAll(financeInfo.getAllSubmittedReceipts());
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
	
	public BillVO checkReceipt(String billID){
		return null;
	}
	
	public ResultMessage modifyReceipt(BillVO bill){
		return null;
	}
	
	public ResultMessage approveReceipt(BillVO bill){
		return null;
	}
	
	public ResultMessage refuseReceipt(BillVO bill){
		return null;
	}
}
