package blservice.userblservice;

import java.util.ArrayList;

import vo.UserVO;

public interface UserInfo {

	public String getCurrentUserID();
	
	public String getCurrentUserNameByID(String UserID);
	
	public ArrayList<UserVO> show();
	
	public UserVO findUserByID(String UserID);
	
	public ArrayList<UserVO> getAllSalesmen();
	
}
