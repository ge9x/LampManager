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
	private int lockNumber = 0;	// 日志系统上锁数，为0时日志系统处于开启状态，>0时为关闭状态

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
		if (lockNumber == 0) {
			if (userInfo == null) {
				userInfo = UserBLFactory.getInfo();
			}
			LogPO toAdd = new LogPO(LocalDateTime.now(), userInfo.getCurrentUserID(), operationType,
					operationObjectType, details);
			// 暂时保留，方便测试效果↓
			ResultMessage ret = logDataService.addLog(toAdd);
			System.out.println(toAdd.toString());

			return ret;
		}
		else {
			return ResultMessage.FAILED;
		}
	}

	public ResultMessage close() {
		lockNumber++;
		return ResultMessage.SUCCESS;
	}

	public ResultMessage open() {
		if (lockNumber > 0) {
			lockNumber--;
		}
		if (lockNumber == 0) {
			return ResultMessage.SUCCESS;
		}
		else {
			return ResultMessage.FAILED;
		}
	}
}
