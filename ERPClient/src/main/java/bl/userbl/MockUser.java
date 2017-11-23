package bl.userbl;

import java.util.ArrayList;

import util.ResultMessage;
import util.UserLimits;
import util.UserPosition;
import vo.UserVO;

public class MockUser extends User{
	
	private UserVO financialStaff = new UserVO("000001", "123456", "Aster", UserPosition.FINANCIAL_STAFF, UserLimits.STAFF);

	@Override
	public ResultMessage login(String userID, String password){
		if(userID.equals(financialStaff.userID)&&password.equals(financialStaff.password)){
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}

	@Override
	public ResultMessage addUser(UserVO vo){
		ArrayList<UserVO> user = new ArrayList<UserVO>();
		user.add(financialStaff);
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage deleteUser(String userID){
		return ResultMessage.SUCCESS;
	}

	@Override
	public ResultMessage modifyUser(UserVO vo){
		if(vo.userID.equals(financialStaff.userID)){
			financialStaff = vo;
			return ResultMessage.SUCCESS;
		}
		else{
			return ResultMessage.NOT_EXIST;
		}
	}
	
	@Override
	public UserVO findUserByID(String UserID){
		if(financialStaff.userID.equals(UserID)){
			return financialStaff;
		}
		return null;
	}
	
	@Override
	public ArrayList<UserVO> show(){
		ArrayList<UserVO> user = new ArrayList<UserVO>();
		user.add(financialStaff);
		return user;
	}
}
