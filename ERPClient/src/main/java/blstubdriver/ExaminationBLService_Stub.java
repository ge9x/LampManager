package blstubdriver;

import java.util.ArrayList;
import java.util.Date;

import blservice.examinationblservice.ExaminationBLService;
import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.BillVO;

public class ExaminationBLService_Stub implements ExaminationBLService{
	
	AccountBillVO accountBill = new AccountBillVO(new Date(), "XJFKD-20171021-00001", BillState.SUBMITTED, BillType.CASH, "ZLK", "Aster",new ArrayList<AccountBillItemVO>());

	public ArrayList<BillVO> show() {
		// TODO Auto-generated method stub
		ArrayList<BillVO> billList = new ArrayList<BillVO>();
		billList.add(accountBill);
		return billList;
	}

	public BillVO checkReceipt(String billID) {
		// TODO Auto-generated method stub
		if(billID == "XJFKD-20171021-00001"){
			return accountBill;
		}
		else{
			System.out.println("Check failed!");
			return null;
		}
	}

	public ResultMessage modifyReceipt(BillVO bill) {
		// TODO Auto-generated method stub
		if(bill.ID.equals("XJFKD-20171021-00001")){
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
