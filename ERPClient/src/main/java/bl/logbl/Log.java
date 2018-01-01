package bl.logbl;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import bl.userbl.UserBLFactory;
import blservice.userblservice.UserInfo;
import dataservice.logdataservice.LogDataService;
import po.LogPO;
import rmi.LogRemoteHelper;
import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * 
 * @author 巽
 *
 */
public class Log {
	private LogDataService logDataService;
	private UserInfo userInfo;
	private boolean isOpen = true;	// 日志系统是否处于开启状态

	protected Log() {
		logDataService = LogRemoteHelper.getInstance().getLogDataService();
	}

	public ArrayList<String> show() throws RemoteException {
		ArrayList<LogPO> logPOs = logDataService.show();
		ArrayList<String> ret = new ArrayList<>();
		for (LogPO po : logPOs) {
			ret.add(po.toString());
		}
		return ret;
	}

	public ArrayList<String> getLogByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
		ArrayList<LogPO> logPOs = logDataService.getLogByDate(start, end);
		ArrayList<String> ret = new ArrayList<>();
		for (LogPO po : logPOs) {
			ret.add(po.toString());
		}
		return ret;
	}

	public ResultMessage record(OperationType operationType, OperationObjectType operationObjectType, String details)
			throws RemoteException {
		if (isOpen) {
			if (userInfo == null) {
				userInfo = UserBLFactory.getInfo();
			}
			LogPO toAdd = new LogPO(LocalDateTime.now(), userInfo.getCurrentUserID(), operationType,
					operationObjectType, details);
			// 暂时保留，方便测试效果↓
			ResultMessage ret = logDataService.addLog(toAdd);
			ArrayList<String> logs = this.show();
			for (String record : logs) {
				System.out.println(record);
			}

			return ret;
		}
		else {
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage close() {
		if (isOpen) {
			isOpen = false;
			return ResultMessage.SUCCESS;
		}
		else {
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage open() {
		if (!isOpen) {
			isOpen = true;
			return ResultMessage.SUCCESS;
		}
		else {
			return ResultMessage.FAILED;
		}
	}
}
