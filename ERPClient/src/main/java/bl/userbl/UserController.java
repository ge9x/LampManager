package bl.userbl;

import java.util.HashMap;

import blservice.userblservice.UserBLService;
import blservice.userblservice.UserInfo;
import util.ResultMessage;
import vo.UserVO;

public class UserController implements UserBLService, UserInfo{

	private User user;
	
	public UserController(){
		user = new User();
	}

	public ResultMessage login(String userID, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage addUser(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage deleteUser(String userID) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultMessage modifyUser(UserVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> getCurrentUserName() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
