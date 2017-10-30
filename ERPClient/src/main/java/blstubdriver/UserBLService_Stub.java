package blstubdriver;

import java.util.ArrayList;

import blservice.userblservice.UserBLService;
import util.ResultMessage;
import vo.UserVO;

public class UserBLService_Stub implements UserBLService{

	public ResultMessage login(String userID, String password) {
		// TODO Auto-generated method stub
		if(userID.equals("test")&&password.equals("123456")){
			return ResultMessage.SUCCESS;
		}
		else if(userID.equals("test")&&!password.equals("123456")){
			return ResultMessage.FAILED;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	public ResultMessage addUser(UserVO vo) {
		// TODO Auto-generated method stub
		if(vo.userID.equals("test")){
			return ResultMessage.EXIST;
		}
		else{
			ArrayList<UserVO> allUser = new ArrayList<UserVO>();
			allUser.add(vo);
			return ResultMessage.SUCCESS;
		}
	}

	public ResultMessage deleteUser(String userID) {
		// TODO Auto-generated method stub
		if(userID.equals("test")){
			System.out.println("Delete user success!");
			return ResultMessage.SUCCESS;
		}
		else{
			System.out.println("User doesn't exist!");
			return ResultMessage.NOT_EXIST;
		}
	}

	public ResultMessage modifyUser(UserVO vo) {
		// TODO Auto-generated method stub
		if(vo.userID.equals("test")){
			System.out.println("Modify user success!");
			return ResultMessage.SUCCESS;
		}
		else{
			System.out.println("User doesn't exist!");
			return ResultMessage.NOT_EXIST;
		}
	}

}
