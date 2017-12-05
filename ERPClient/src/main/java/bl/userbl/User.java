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
		return re;
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

	public ArrayList<UserVO> show() throws RemoteException{
		if(userDataService.show()!=null&&userDataService.show().size()>0){
			userPOs = userDataService.show();
			ArrayList<UserVO> userVOs = new ArrayList<>();
			for(UserPO po:userPOs){
				userVOs.add(poTOvo(po));
			}
			return userVOs;
		}
		else{
			return null;
		}
	}
	
	public String getCurrentUserID(){
		return currentUserID;
	}

	public ArrayList<UserVO> findUsersByKeywords(String keywords) throws RemoteException{
		ArrayList<UserPO> resultPOs = userDataService.findUsersByKeyword(keywords);
		ArrayList<UserVO> resultVOs = new ArrayList<>();
		for(UserPO po:resultPOs){
			resultVOs.add(poTOvo(po));
		}
		return resultVOs;
	}

	public ArrayList<UserVO> findUsersByID(String UserID) throws RemoteException{
		ArrayList<UserPO> resultPOs = userDataService.findUsersByID(UserID);
		ArrayList<UserVO> resultVOs = new ArrayList<>();
		for(UserPO po:resultPOs){
			resultVOs.add(poTOvo(po));
		}
		return resultVOs;
	}
	
	public UserVO findUserByID(String UserID) throws RemoteException{
		UserPO user = userDataService.find(UserID);
		return poTOvo(user);
	}
	
	public UserPO voTOpo(UserVO userVO){
		return new UserPO(userVO.password, userVO.name, userVO.position, userVO.limit);
	}
	
	public UserVO poTOvo(UserPO userPO){
		return new UserVO(String.valueOf(userPO.getUserID()), userPO.getPassword(), userPO.getName(), userPO.getPosition(), userPO.getLimit());
	}
}
