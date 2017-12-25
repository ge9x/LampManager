package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;

import dataimpl.logdataimpl.LogDataServiceImpl;
import dataservice.logdataservice.LogDataService;
import po.LogPO;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * @author 巽
 *
 */
public class LogDataRemoteObject extends UnicastRemoteObject implements LogDataService{
	private static final long serialVersionUID = 2875949832305621410L;
	LogDataService logDataService;

	protected LogDataRemoteObject() throws RemoteException {
		super();
		logDataService = LogDataServiceImpl.getInstance();
	}

	@Override
	public ArrayList<LogPO> show() throws RemoteException {
		return logDataService.show();
	}

	@Override
	public ArrayList<LogPO> getLogByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
		return logDataService.getLogByDate(start, end);
	}

	@Override
	public ResultMessage addLog(LogPO po) throws RemoteException {
		return logDataService.addLog(po);
	}

}
