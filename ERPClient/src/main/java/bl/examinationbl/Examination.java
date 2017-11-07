package bl.examinationbl;

import java.util.ArrayList;
import java.util.Date;

import dataservice.examinationdataservice.ExaminationDataService;
import util.ResultMessage;
import vo.BillVO;

public class Examination {

	private BillVO vo;
	private ExaminationDataService examinationDataService;
	
	public Examination(){
		
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
