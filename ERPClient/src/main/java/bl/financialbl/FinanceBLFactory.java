package bl.financialbl;

import blservice.financeblservice.FinanceBLService;
import blservice.financeblservice.FinanceInfo;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class FinanceBLFactory {
	private static FinanceController financeController;

	public synchronized static FinanceBLService getBLService() {
		if (financeController == null) {
			financeController = new FinanceController();
		}
		return financeController;
	}

	public synchronized static FinanceInfo getInfo() {
		if (financeController == null) {
			financeController = new FinanceController();
		}
		return financeController;
	}
}
