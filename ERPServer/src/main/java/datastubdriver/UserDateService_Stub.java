package datastubdriver;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.userdataservice.UserDataService;
import po.UserPO;
import util.ResultMessage;
import util.UserLimits;
import util.UserPosition;

public class UserDateService_Stub implements UserDataService{
	ArrayList<UserPO> userInfo = new ArrayList<UserPO>();
	
	public UserPO find(String userID) throws RemoteException {
		// TODO Auto-generated method stub
		for(UserPO po : userInfo){
			if(po.getUserID().equals(userID)){
				System.out.println("Find user success!");
				return po;
			}
		}
		System.out.println("User doesn't exist!");
		return null;
	}

	public ResultMessage add(UserPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(UserPO i : userInfo){
			if(i.getUserID().equals(po.getUserID())){
				System.out.println("User has existed!");
				return ResultMessage.EXIST;
			}
		}
		System.out.println("Add user success!");
		return ResultMessage.SUCCESS;
	}

	public ResultMessage delete(UserPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(UserPO i : userInfo){
			if(i.getUserID().equals(po.getUserID())){
				userInfo.remove(i);
				System.out.println("Delete user success!");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("User doesn't exist!");
		return ResultMessage.NOT_EXIST;
	}

	public ResultMessage update(UserPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(UserPO i : userInfo){
			if(i.getUserID().equals(po.getUserID())){
				userInfo.remove(i);
				userInfo.add(po);
				System.out.println("Update user success!");
				return ResultMessage.SUCCESS;
			}
		}
		System.out.println("User doesn't exist!");
		return ResultMessage.NOT_EXIST;
	}

	public void init() throws RemoteException {
		// TODO Auto-generated method stub
		userInfo.clear();
		userInfo.add(new UserPO("admin", "admin", "admin", UserPosition.ADMIN, UserLimits.STAFF));
		System.out.println("Initial user information success!");
	}

}
