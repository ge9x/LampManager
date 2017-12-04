package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.userdataimpl.UserDataServiceImpl;
import dataservice.userdataservice.UserDataService;
import po.UserPO;
import util.ResultMessage;

public class UserDataRemoteObject extends UnicastRemoteObject implements UserDataService{
	
	private UserDataService userDataService;
	
	protected UserDataRemoteObject() throws RemoteException{
		userDataService = UserDataServiceImpl.getInstance();
	}
	
	@Override
	public UserPO find(String userID) throws RemoteException{
		return userDataService.find(userID);
	}
	
	@Override
	public ResultMessage add(UserPO po) throws RemoteException{
		return userDataService.add(po);
	}
	
	@Override
	public ResultMessage delete(String userID) throws RemoteException{
		return userDataService.delete(userID);
	}
	
	@Override
	public ResultMessage update(UserPO po) throws RemoteException{
		return userDataService.update(po);
	}
	
	@Override
	public void init() throws RemoteException{
		userDataService.init();
	}
	
	@Override
	public ResultMessage login(String userID, String password) throws RemoteException{
		return userDataService.login(userID, password);
	}

	@Override
	public ArrayList<UserPO> show() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserPO> findUsersByKeyword(String keyword) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserPO> findUsersByID(String userID) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
