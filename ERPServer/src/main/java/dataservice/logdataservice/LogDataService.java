package dataservice.logdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import po.LogPO;
import util.ResultMessage;

/**
 * Created on 2017/12/25
 * 
 * @author 巽
 *
 */
public interface LogDataService extends Remote {
	/**
	 * 得到所有日志记录
	 * 
	 * @return 所有日志记录的集合
	 * @throws RemoteException
	 */
	public ArrayList<LogPO> show() throws RemoteException;

	/**
	 * 根据时间段查找日志记录
	 * 
	 * @param start 起始日期
	 * @param end 结束日期
	 * @return 该时间段内的日志记录的集合
	 * @throws RemoteException
	 */
	public ArrayList<LogPO> getLogByDate(LocalDateTime start, LocalDateTime end) throws RemoteException;

	/**
	 * 添加日志记录
	 * 
	 * @param po 待添加的日志记录PO
	 * @return 是否添加成功
	 * @throws RemoteException
	 */
	public ResultMessage addLog(LogPO po) throws RemoteException;
}
