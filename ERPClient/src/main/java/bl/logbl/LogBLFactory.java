package bl.logbl;

import blservice.logblservice.LogBLService;
import blservice.logblservice.LogInfo;

/**
 * Created on 2017/12/29
 * @author 巽
 *
 */
public class LogBLFactory {
	private static LogController logController;

	public synchronized static LogBLService getBLService() {
		if (logController == null) {
			logController = new LogController();
		}
		return logController;
	}

	public synchronized static LogInfo getInfo() {
		return new LogController();	// 因为日志系统持有开启/关闭状态，所以不适用单例模式
	}
}
