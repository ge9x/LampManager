package blstubdriver;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import blservice.examinationblservice.ExaminationBLService;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.AccountVO;
import vo.BillVO;
import vo.GoodsVO;
import vo.InventoryBillVO;

public class ExaminationBLService_Stub implements ExaminationBLService{
	
	ArrayList<BillVO> billList = new ArrayList<BillVO>();
	ArrayList<AccountBillItemVO> billItem = new ArrayList<AccountBillItemVO>();
	{
		AccountVO account = new AccountVO("001","工商银行账户1",2000);
		AccountBillItemVO Item = new AccountBillItemVO(account, 2000, "无");
		billItem.add(Item);
	}
	AccountBillVO accountBill = new AccountBillVO(new Date(), "XSFKD-20171021-00001", BillState.SUBMITTED, BillType.PAYMENT, "ZLK", "Aster",billItem);
	public ArrayList<BillVO> show() {
		// TODO Auto-generated method stub
		billList.add(accountBill);
		return billList;
	}

	public BillVO checkReceipt(String billID) {
		// TODO Auto-generated method stub
		if(billID == "XSFKD-20171021-00001"){
			return accountBill;
		}
		else{
			System.out.println("Check failed!");
			return null;
		}
	}

	public ResultMessage modifyReceipt(BillVO bill) {
		// TODO Auto-generated method stub
		if(bill.ID.equals("XSFKD-20171021-00001")){
			accountBill = (AccountBillVO)bill;
			System.out.println("Modify Bill Success!");
			return ResultMessage.SUCCESS;
		}
		else{
			System.out.println("Modify Bill Failed!");
			return ResultMessage.NOT_EXIST;
		}
	}

	public ResultMessage approveReceipt(BillVO bill) {
		// TODO Auto-generated method stub
		bill.state = BillState.PASS;
		return ResultMessage.SUCCESS;
	}

	public ArrayList<BillVO> showHistory() {
		// TODO Auto-generated method stub
		ArrayList<BillVO> history = new ArrayList<BillVO>();
		history.add(accountBill);
		return history;
	}

}
