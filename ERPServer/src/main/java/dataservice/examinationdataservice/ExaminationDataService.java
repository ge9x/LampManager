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
     * 按审批时间段查找返回相应的ExaminationPO结果
     * 
     * @param startEnd, endDate
     * @return ArrayList<ExaminationPO>
     */
	public ArrayList<BillPO> finds(Date startDate, Date endDate) throws RemoteException;
	
	public ResultMessage add(BillPO po) throws RemoteException;
	
	public void init() throws RemoteException;
}
