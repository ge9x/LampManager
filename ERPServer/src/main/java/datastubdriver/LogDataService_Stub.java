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
 * 
 * @author 巽
 *
 */
public class LogDataService_Stub implements LogDataService {
	private ArrayList<LogPO> logs;

	{
		logs = new ArrayList<>();
		logs.add(new LogPO(LocalDateTime.now(), "00001", OperationType.ADD, OperationObjectType.GOODS,
				"ID:0300001, 名称:圣洁牌经典黑白配落地灯, 型号:SJ-0001, 所属分类:落地灯, 警戒数量:250, 进价:233, 零售价:250, 最近进价:233.3, 最近零售价: 250.5, 总数量: 600"));
	}

	@Override
	public ArrayList<LogPO> show() throws RemoteException {
		return logs;
	}

	@Override
	public ArrayList<LogPO> getLogByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
		if (start.isEqual(logs.get(0).getDateAndTime())) {
			return logs;
		}
		return new ArrayList<>();
	}

	@Override
	public ResultMessage addLog(LogPO po) throws RemoteException {
		if (po.getUserID().equals("00001")) {
			logs.add(po);
			return ResultMessage.SUCCESS;
		}
		return ResultMessage.FAILED;
	}

}
