package dataimpl.userdataimpl;

import java.rmi.RemoteException;

import datahelper.DataHelper;
import datahelper.HibernateDataHelper;
import dataservice.userdataservice.UserDataService;
import po.UserPO;
import util.ResultMessage;

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
	public UserPO find(int userID) throws RemoteException {
		return userDataHelper.exactlyQuery("id", userID);
	}

	@Override
	public ResultMessage add(UserPO po) throws RemoteException {
		return userDataHelper.save(po);
	}

	@Override
	public ResultMessage delete(UserPO po) throws RemoteException {
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

}
