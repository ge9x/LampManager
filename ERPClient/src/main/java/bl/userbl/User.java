package bl.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import dataservice.userdataservice.UserDataService;
import po.UserPO;
import rmi.UserRemoteHelper;
import util.ResultMessage;
import vo.UserVO;

public class User {

	private UserDataService userDataService;
	private static String currentUserID;
	ArrayList<UserPO> userPOs;
	
	public User(){
		userDataService = UserRemoteHelper.getInstance().getUserDataService();
	}
	
	public ResultMessage login(String userID, String password) throws RemoteException{
		ResultMessage re = userDataService.login(userID, password);
		if(re==ResultMessage.SUCCESS){
			currentUserID = userID;
		}
		return re;
	}

	public ResultMessage addUser(UserVO vo) throws RemoteException{
		UserPO userPO = voTOpo(vo);
		ResultMessage re = userDataService.add(userPO);
		return null;
	}

	public ResultMessage deleteUser(String userID) throws RemoteException{
		ResultMessage re = userDataService.delete(userID);
		return re;
	}

	public ResultMessage modifyUser(UserVO vo) throws RemoteException{
		for(UserPO userPO:userPOs){
			if(userPO.getUserID()==Integer.parseInt(vo.userID)){
				userPO.setPassword(vo.password);
				userPO.setName(vo.name);
				userPO.setPosition(vo.position);
				userPO.setLimit(vo.limit);
				return userDataService.update(userPO);
			}
		}
		return ResultMessage.FAILED;
	}
	
	public HashMap<String, String> getCurrentUserName() throws RemoteException{
		HashMap<String, String> user = new HashMap<>();
		user.put(currentUserID, userDataService.find(currentUserID).getName());
		return user;
	}

	public String getCurrentUserID() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCurrentUserNameByID(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UserVO> show() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UserVO> findUsersByID(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<UserVO> findUsersByKeywords(String keywords) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserVO findUserByID(String UserID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserPO voTOpo(UserVO userVO){
		return new UserPO(userVO.password, userVO.name, userVO.position, userVO.limit);
	}
	
	public UserVO poTOvo(UserPO userPO){
		return new UserVO(String.valueOf(userPO.getUserID()), userPO.getPassword(), userPO.getName(), userPO.getPosition(), userPO.getLimit());
	}
}
