package datastubdriver;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dataservice.logdataservice.LogDataService;
import po.LogPO;
import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * @author 巽
 *
 */
public class LogDataService_Stub implements LogDataService{
	private ArrayList<LogPO> logs;
	
	{
		logs = new ArrayList<>();
		logs.add(new LogPO(LocalDateTime.now(), "00001", OperationType.ADD, OperationObjectType.CLASSIFICATION, ""));
	}

	@Override
	public ArrayList<LogPO> show() throws RemoteException {
		return logs;
	}

	@Override
	public ArrayList<LogPO> getLogByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
		if(start.isEqual(logs.get(0).getDateAndTime())){
			return logs;
		}
		return new ArrayList<>();
	}

	@Override
	public ResultMessage addLog(LogPO po) throws RemoteException {
		if(po.getUserID().equals("00001")){
			logs.add(po);
			return ResultMessage.SUCCESS;
		}
		return ResultMessage.FAILED;
	}

}
