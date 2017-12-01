package dataservice.examinationdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import po.BillPO;
import util.ResultMessage;

/** 
 * Created by Aster on 2017/10/21
 */
public interface ExaminationDataService {
	
	/**
     * 按审批时间段查找返回相应的已审批的BillVO结果
     * 
     * @param startEnd, endDate
     * @return ArrayList<BillPO>
     */
	public ArrayList<BillPO> finds(Date startDate, Date endDate) throws RemoteException;
	/**
	 * 显示所有的申请单据
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<BillPO> show() throws RemoteException;
}
