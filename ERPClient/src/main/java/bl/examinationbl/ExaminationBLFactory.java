package bl.examinationbl;

import blservice.examinationblservice.ExaminationBLService;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class ExaminationBLFactory {
	private static ExaminationController examinationController;

	public synchronized static ExaminationBLService getBLService() {
		if (examinationController == null) {
			examinationController = new ExaminationController();
		}
		return examinationController;
	}
}
