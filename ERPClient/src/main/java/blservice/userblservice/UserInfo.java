package blservice.userblservice;

import java.util.ArrayList;
import java.util.HashMap;

import vo.UserVO;

public interface UserInfo {

	public HashMap<String, String> getCurrentUserName();
	
	public ArrayList<UserVO> getAllUsers();
	
	public UserVO findUserByID(String UserID);
}
