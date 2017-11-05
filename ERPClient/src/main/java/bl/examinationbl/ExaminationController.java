package bl.examinationbl;

import java.util.ArrayList;

import blservice.examinationblservice.ExaminationBLService;
import util.ResultMessage;
import vo.BillVO;

public class ExaminationController implements ExaminationBLService{
	
	private Examination examination;
	
	public ExaminationController(){
		examination = new Examination();
	}

	public ArrayList<BillVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	public BillVO checkReceipt(String billID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage modifyReceipt(BillVO bill) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage approveReceipt(BillVO bill) {
		// TODO Auto-generated method stub
		return null;
	}

}
