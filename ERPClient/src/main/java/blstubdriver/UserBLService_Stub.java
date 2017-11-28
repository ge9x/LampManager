package blstubdriver;

import java.util.ArrayList;

import blservice.userblservice.UserBLService;
import util.ResultMessage;
import util.UserLimits;
import util.UserPosition;
import vo.UserVO;

public class UserBLService_Stub implements UserBLService{
	ArrayList<UserVO> users = new ArrayList<UserVO>();
	{
		UserVO user1 = new UserVO("0012", "1234", "InventoryStaff", UserPosition.INVENTORY_STAFF, UserLimits.STAFF);
		UserVO user2 = new UserVO("0033", "1234", "SalesStaff", UserPosition.SALES_STAFF, UserLimits.STAFF);
		UserVO user3 = new UserVO("0047", "1234", "FinancialStaff", UserPosition.FINANCIAL_STAFF, UserLimits.MANAGER);
		UserVO user4 = new UserVO("0026", "1234", "GeneralManager", UserPosition.GENERAL_MANAGER, UserLimits.STAFF);
		UserVO user5 = new UserVO("0001", "1234", "Admin", UserPosition.ADMIN, UserLimits.STAFF);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
	}

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

	public ArrayList<UserVO> findUsersByID(String UserID) {
		// TODO Auto-generated method stub
		ArrayList<UserVO> result = new ArrayList<UserVO>();
		for(int i=0;i<users.size();i++){
			if(users.get(i).userID.contains(UserID)){
				result.add(users.get(i));
			}
		}
		return result;
	}

	public ArrayList<UserVO> show() {
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public String getCurrentUserID() {
		// TODO Auto-generated method stub
		return new UserVO("0047", "1234", "FinancialStaff", UserPosition.FINANCIAL_STAFF, UserLimits.MANAGER).userID;
	}

	public ArrayList<UserVO> findUsersByKeywords(String keywords) {
		// TODO Auto-generated method stub
		ArrayList<UserVO> result = new ArrayList<UserVO>();
		for(int i=0;i<users.size();i++){
			if(users.get(i).name.contains(keywords)){
				result.add(users.get(i));
			}
		}
		return result;
	}
}
