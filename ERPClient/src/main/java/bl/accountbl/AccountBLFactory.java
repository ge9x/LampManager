package bl.accountbl;

import blservice.accountblservice.AccountBLService;
import blservice.accountblservice.AccountInfo;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class AccountBLFactory {
	private static AccountController accountController;

	public synchronized static AccountBLService getBLService() {
		if (accountController == null) {
			accountController = new AccountController();
		}
		return accountController;
	}

	public synchronized static AccountInfo getInfo() {
		if (accountController == null) {
			accountController = new AccountController();
		}
		return accountController;
	}
}
