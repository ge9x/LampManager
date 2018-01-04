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
	
	/**
	 * 添加用户
	 * 
	 * @param UserPO
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage add(UserPO po) throws RemoteException;
	
	/**
	 * 删除用户
	 * 
	 * @param userID
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage delete(String userID) throws RemoteException;
	
	/**
	 * 更新用户信息
	 * 
	 * @param UserPO
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage update(UserPO po) throws RemoteException;
	
	/**
	 * 初始化
	 * 
	 * @throws RemoteException
	 */
	public void init() throws RemoteException;
	
	/**
	 * 验证用户登录信息
	 * 
	 * @param userID
	 * @param password
	 * @return ResultMessage
	 * @throws RemoteException
	 */
	public ResultMessage login(String userID, String password) throws RemoteException;
	
	/**
	 * 得到所有用户信息
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<UserPO> show() throws RemoteException;
	
	/**
	 * 通过关键字模糊查找用户
	 * 
	 * @param keyword
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<UserPO> findUsersByKeyword(String keyword) throws RemoteException;
	
	/**
	 * 通过用户ID模糊查找用户
	 * 
	 * @param userID
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<UserPO> findUsersByID(String userID) throws RemoteException;
}
