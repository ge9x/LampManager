package bl.userbl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import blservice.userblservice.UserBLService;
import blservice.userblservice.UserInfo;
import util.ResultMessage;
import vo.UserVO;

public class UserController implements UserBLService, UserInfo{

	private User user;
	
	protected UserController(){
		user = new User();
	}

	public ResultMessage login(String userID, String password) {
		try {
            return user.login(userID, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
	}

	public ResultMessage addUser(UserVO vo) {
		try {
            return user.addUser(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
	}

	public ResultMessage deleteUser(String userID) {
		try {
            return user.deleteUser(userID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
	}

	public ResultMessage modifyUser(UserVO vo) {
		try {
            return user.modifyUser(vo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
	}

	public String getCurrentUserID() {
		return user.getCurrentUserID();
	}

	public String getCurrentUserNameByID(String UserID) {
		try {
			return user.findUserByID(UserID).name;
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<UserVO> show() {
		try {
			return user.show();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<UserVO> findUsersByID(String UserID) {
		try {
			return user.findUsersByID(UserID);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<UserVO> findUsersByKeywords(String keywords) {
		try {
			return user.findUsersByKeywords(keywords);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserVO findUserByID(String UserID) {
		try {
			return user.findUserByID(UserID);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ArrayList<UserVO> getAllSalesmen() {
		// TODO Auto-generated method stub
		try {
			return user.getAllSalesmen();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
		
}
