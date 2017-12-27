package blservice.logblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created on 2-17/12/25
 * @author 巽
 *
 */
public interface LogBLService extends Remote{
	/**
	 * 得到所有日志记录
	 * 
	 * @return 所有日志记录的集合
	 * @throws RemoteException
	 */
	public ArrayList<String> show();

	/**
	 * 根据时间段查找日志记录
	 * 
	 * @param start 起始日期
	 * @param end 结束日期
	 * @return 该时间段内的日志记录的集合
	 * @throws RemoteException
	 */
	public ArrayList<String> getLogByDate(LocalDateTime start, LocalDateTime end);
}
