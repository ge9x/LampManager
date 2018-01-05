package bl.examinationbl;

import java.util.ArrayList;

import blservice.examinationblservice.ExaminationBLService;
import util.ResultMessage;
import vo.BillVO;

public class ExaminationController implements ExaminationBLService{
	
	private Examination examination;
	
	protected ExaminationController(){
		examination = new Examination();
	}

	public ArrayList<BillVO> show() {
		return examination.show();
	}

	public ResultMessage modifyReceipt(BillVO bill) {
		return examination.modifyReceipt(bill);
	}

	public ResultMessage approveReceipt(BillVO bill) {
		return examination.approveReceipt(bill);
	}

	public ResultMessage refuseReceipt(BillVO bill) {
		return examination.refuseReceipt(bill);
	}

}
