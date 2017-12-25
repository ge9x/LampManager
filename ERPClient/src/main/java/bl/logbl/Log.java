package bl.logbl;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dataservice.logdataservice.LogDataService;
import po.LogPO;
import rmi.LogRemoteHelper;
import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * @author 巽
 *
 */
public class Log {
	private LogDataService logDataService;
	
	public Log(){
		logDataService = LogRemoteHelper.getInstance().getLogDataService();
	}

	public ArrayList<String> show() throws RemoteException {
		ArrayList<LogPO> logPOs = logDataService.show();
		ArrayList<String> ret = new ArrayList<>();
		for(LogPO po : logPOs){
			ret.add(po.toString());
		}
		return ret;
	}

	public ArrayList<String> getLogByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
		ArrayList<LogPO> logPOs = logDataService.getLogByDate(start, end);
		ArrayList<String> ret = new ArrayList<>();
		for(LogPO po : logPOs){
			ret.add(po.toString());
		}
		return ret;
	}

	public ResultMessage record(String userID, OperationType operationType, OperationObjectType operationObjectType,
			String details) throws RemoteException {
		LogPO toAdd = new LogPO(LocalDateTime.now(), userID, operationType, operationObjectType, details);
		return logDataService.addLog(toAdd);
	}
}
