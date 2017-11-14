package bl.userbl;

import dataservice.userdataservice.UserDataService;
import util.ResultMessage;
import vo.UserVO;

public class User {

	private UserVO vo;
	private UserDataService userDataService;
	
	public User(){
		
	}
	
	public ResultMessage login(String userID, String password){
		return null;
	}

	public ResultMessage addUser(UserVO vo){
		return null;
	}

	public ResultMessage deleteUser(String userID){
		return null;
	}

	public ResultMessage modifyUser(UserVO vo){
		return null;
	}
	
	public UserVO findUserByID(String UserID){
		return null;
	}
	
}
