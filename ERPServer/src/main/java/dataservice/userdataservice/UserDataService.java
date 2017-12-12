package dataservice.userdataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.UserPO;
import util.ResultMessage;

/** 
 * Created by Aster on 2017/10/21
 */
public interface UserDataService extends Remote{

	/**
     * 按用户ID进行查找返回相应的UserPO结果
     * 
     * @param userID
     * @return UserPO
     */
	public UserPO find(String userID) throws RemoteException;
	
	public ResultMessage add(UserPO po) throws RemoteException;
	
	public ResultMessage delete(String userID) throws RemoteException;
	
	public ResultMessage update(UserPO po) throws RemoteException;
	
	public void init() throws RemoteException;
	
	public ResultMessage login(String userID, String password) throws RemoteException;
	
	public ArrayList<UserPO> show() throws RemoteException;
	
	public ArrayList<UserPO> findUsersByKeyword(String keyword) throws RemoteException;
	
	public ArrayList<UserPO> findUsersByID(String userID) throws RemoteException;
}
