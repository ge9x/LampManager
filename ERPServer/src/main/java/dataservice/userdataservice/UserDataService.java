package dataservice.userdataservice;

import java.rmi.RemoteException;

import po.UserPO;
import util.ResultMessage;

/** 
 * Created by Aster on 2017/10/21
 */
public interface UserDataService {

	/**
     * 按用户ID进行查找返回相应的UserPO结果
     * 
     * @param userID
     * @return UserPO
     */
	public UserPO find(int userID) throws RemoteException;
	
	public ResultMessage add(UserPO po) throws RemoteException;
	
	public ResultMessage delete(UserPO po) throws RemoteException;
	
	public ResultMessage update(UserPO po) throws RemoteException;
	
	public void init() throws RemoteException;
}
