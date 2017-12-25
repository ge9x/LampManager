package rmi;

import java.rmi.Remote;

import dataservice.logdataservice.LogDataService;

/**
 * Created on 2017/12/25
 * 
 * @author 巽
 *
 */
public class LogRemoteHelper {
	private Remote remote;
	private static LogRemoteHelper inventoryionRemoteHelper = new LogRemoteHelper();

	public static LogRemoteHelper getInstance() {
		return inventoryionRemoteHelper;
	}

	private LogRemoteHelper() { }

	public void setRemote(Remote remote) {
		this.remote = remote;
	}

	public LogDataService getLogDataService() {
		return (LogDataService) remote;
	}
}
