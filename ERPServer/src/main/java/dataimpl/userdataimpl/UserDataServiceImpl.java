package dataimpl.userdataimpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.userdataservice.UserDataService;
import po.UserPO;
import util.ResultMessage;

/**
 * Created on 2017/11/29
 * @author 巽
 *
 */
public class UserDataServiceImpl implements UserDataService{
	private static UserDataServiceImpl userDataServiceImpl;
	private DataHelper<UserPO> userDataHelper;
	
	public static UserDataServiceImpl getInstance(){
		if(userDataServiceImpl == null){
			userDataServiceImpl = new UserDataServiceImpl();
		}
		return userDataServiceImpl;
	}
	
	private UserDataServiceImpl(){
		this.userDataHelper = new HibernateDataHelper<UserPO>(UserPO.class);
	}

	@Override
	public UserPO find(String userID) throws RemoteException {
		return userDataHelper.exactlyQuery("userID", userID);
	}

	@Override
	public ResultMessage add(UserPO po) throws RemoteException {
		return userDataHelper.save(po);
	}

	@Override
	public ResultMessage delete(String userID) throws RemoteException {
		UserPO po = userDataHelper.exactlyQuery("userID", Integer.parseInt(userID));
		return userDataHelper.delete(po);
	}

	@Override
	public ResultMessage update(UserPO po) throws RemoteException {
		return userDataHelper.update(po);
	}

	@Override
	public void init() throws RemoteException {
		// TODO 喵喵喵???
	}

	@Override
	public ResultMessage login(String userID, String password) throws RemoteException {
		UserPO po = userDataHelper.exactlyQuery("userID", Integer.parseInt(userID));
		if(po == null){
			return ResultMessage.NOT_EXIST;
		}
		else if (po.getPassword().equals(password)){
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.FAILED;
		}
	}

	@Override
	public ArrayList<UserPO> show() throws RemoteException {
		return userDataHelper.fullyQuery(null, null);
	}

	@Override
	public ArrayList<UserPO> findUsersByKeyword(String keyword) throws RemoteException {
		return userDataHelper.fuzzyQuery("name", keyword);
	}

	@Override
	public ArrayList<UserPO> findUsersByID(String userID) throws RemoteException {
		if(!userID.isEmpty() && userID.charAt(0) == '0'){	// 对于输入“002”的情况：自己数……
			ArrayList<UserPO> all = show();
			ArrayList<UserPO> ret = new ArrayList<>();
			for(UserPO po : all){
				if(String.format("%05d", po.getUserID()).contains(userID)){
					ret.add(po);
				}
			}
			return ret;
		}
		else{
			return userDataHelper.fuzzyQuery("id", userID);
		}
	}

}
