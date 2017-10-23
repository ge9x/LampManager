package vo;

import java.util.Date;

/** 
 * Created by Aster on 2017/10/20
 */

public class ExaminationVO {
	public BillVO bill;
	
	public Date examinationDate;
	
	public ExaminationVO(BillVO bill, Date examinationDate){
		this.bill = bill;
		this.examinationDate = examinationDate;
	}
}
