package bl.examinationbl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import util.BillState;
import util.BillType;
import util.ResultMessage;
import vo.AccountBillItemVO;
import vo.AccountBillVO;
import vo.BillVO;

public class MockExamination extends Examination{

	private AccountBillVO accountBill = new AccountBillVO(LocalDate.now().toString(), "XJFKD-20171021-00001", BillState.SUBMITTED, BillType.CASH, "ZLK", "Aster",new ArrayList<AccountBillItemVO>());
	
	@Override
	public ArrayList<BillVO> show(){
		ArrayList<BillVO> bill = new ArrayList<BillVO>();
		bill.add(accountBill);
		return bill;
	}
	
	@Override
	public ResultMessage modifyReceipt(BillVO bill){
		if(bill.ID.equals(accountBill.ID)){
			accountBill = (AccountBillVO)bill;
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}
	
	@Override
	public ResultMessage approveReceipt(BillVO bill){
		if(bill.ID.equals(accountBill.ID)){
			accountBill.state = BillState.PASS;
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}
	
}
