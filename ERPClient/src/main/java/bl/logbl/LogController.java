package bl.logbl;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import blservice.logblservice.LogBLService;
import blservice.logblservice.LogInfo;
import util.OperationObjectType;
import util.OperationType;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * 
 * @author 巽
 *
 */
public class LogController implements LogBLService, LogInfo{
	private Log log;

	public LogController() {
		log = new Log();
	}

	@Override
	public ArrayList<String> show() {
		try {
			return log.show();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<String> getLogByDate(LocalDateTime start, LocalDateTime end) {
		try {
			return log.getLogByDate(start, end);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ResultMessage record(String userID, OperationType operationType, OperationObjectType operationObjectType,
			String details) {
		try {
			return log.record(userID, operationType, operationObjectType, details);
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}

}
