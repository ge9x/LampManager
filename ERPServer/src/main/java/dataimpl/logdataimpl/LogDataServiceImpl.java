package dataimpl.logdataimpl;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.logdataservice.LogDataService;
import po.LogPO;
import util.ResultMessage;

public class LogDataServiceImpl implements LogDataService{
	private static LogDataServiceImpl logDataServiceImpl;
	private DataHelper<LogPO> logDataHelper;

	public static LogDataServiceImpl getInstance(){
		if(logDataServiceImpl == null){
			logDataServiceImpl = new LogDataServiceImpl();
		}
		return logDataServiceImpl;
	}
	
	private LogDataServiceImpl(){
		logDataHelper = new HibernateDataHelper<LogPO>(LogPO.class);
	}

	@Override
	public ArrayList<LogPO> show() throws RemoteException {
		return logDataHelper.fullyQuery(null, null);
	}

	@Override
	public ArrayList<LogPO> getLogByDate(LocalDateTime start, LocalDateTime end) throws RemoteException {
		return logDataHelper.rangeQuery("dateAndTime", start, end);
	}

	@Override
	public ResultMessage addLog(LogPO po) throws RemoteException {
		return logDataHelper.save(po);
	}

}
