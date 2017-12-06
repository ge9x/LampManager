package bl.examinationbl;

import java.util.ArrayList;

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
	
	public Examination(){
		examinationDataService = ExaminationRemoteHelper.getInstance().getExaminationDataService();
	}
	

	public ArrayList<BillVO> show(){
		return null;
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
	
	public ArrayList<BillVO> showHistory() {
		return null;
	}
}
