package bl.formbl;

import blservice.formblservice.FormBLService;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class FormBLFactory {
	private static FormController formController;

	public synchronized static FormBLService getBLService() {
		if (formController == null) {
			formController = new FormController();
		}
		return formController;
	}
}
