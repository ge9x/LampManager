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

	public ResultMessage add(UserPO po) throws RemoteException {
		// TODO Auto-generated method stub
		for(UserPO i : userInfo){
			if(i.getUserID() == po.getUserID()){
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
			if(i.getUserID() == po.getUserID()){
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
			if(i.getUserID() == po.getUserID()){
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
		userInfo.add(new UserPO(1, "admin", "admin", UserPosition.ADMIN, UserLimits.STAFF));
		System.out.println("Initial user information success!");
	}

	@Override
	public UserPO find(String userID) throws RemoteException {
		for(UserPO po : userInfo){
			if(po.getUserID().equals(userID)){
				System.out.println("Find user success!");
				return po;
			}
		}
		System.out.println("User doesn't exist!");
		return null;
	}

	@Override
	public ResultMessage delete(String userID) throws RemoteException {
		for(UserPO po : userInfo){
			if(po.getUserID().equals(userID)){
				userInfo.remove(po);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.NOT_EXIST;
	}

	@Override
	public ResultMessage login(String userID, String password) throws RemoteException {
		for(UserPO po : userInfo){
			if(po.getUserID().equals(userID)){
				if(po.getPassword().equals(password)){
					return ResultMessage.SUCCESS;
				}
				else{
					return ResultMessage.FAILED;
				}
			}
		}
		return ResultMessage.NOT_EXIST;
	}

	@Override
	public ArrayList<UserPO> show() throws RemoteException {
		return userInfo;
	}

	@Override
	public ArrayList<UserPO> findUsersByKeyword(String keyword) throws RemoteException {
		ArrayList<UserPO> ret = new ArrayList<>();
		for(UserPO po : userInfo){
			if(po.getName().contains(keyword)){
				ret.add(po);
			}
		}
		return ret;
	}

	@Override
	public ArrayList<UserPO> findUsersByID(String userID) throws RemoteException {
		ArrayList<UserPO> ret = new ArrayList<>();
		for(UserPO po : userInfo){
			if(Integer.valueOf(po.getUserID()).toString().contains(userID)){
				ret.add(po);
			}
		}
		return ret;
	}

}
