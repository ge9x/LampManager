package po;

import java.util.Date;

public class ExaminationPO {
	private BillPO bill;
	
	private Date examinationDate;
	
	public ExaminationPO(BillPO bill, Date examinationDate){
		this.bill = bill;
		this.examinationDate = examinationDate;
	}

	public BillPO getBill() {
		return bill;
	}

	public void setBill(BillPO bill) {
		this.bill = bill;
	}

	public Date getExaminationDate() {
		return examinationDate;
	}

	public void setExaminationDate(Date examinationDate) {
		this.examinationDate = examinationDate;
	}
}
