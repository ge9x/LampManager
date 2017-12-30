package bl.initializationbl;

import blservice.initializationblservice.InitInfo;
import blservice.initializationblservice.InitializationBLService;

/**
 * Created on 2017/12/30
 * @author 巽
 *
 */
public class InitializationBLFactory {
	private static InitializationController initializationController;

	public synchronized static InitializationBLService getBLService() {
		if (initializationController == null) {
			initializationController = new InitializationController();
		}
		return initializationController;
	}

	public synchronized static InitInfo getInfo() {
		if (initializationController == null) {
			initializationController = new InitializationController();
		}
		return initializationController;
	}
}
