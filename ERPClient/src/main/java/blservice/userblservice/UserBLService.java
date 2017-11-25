package blservice.userblservice;

import java.util.ArrayList;

import util.ResultMessage;
import vo.UserVO;

/** 
 * Created by Aster on 2017/10/20
 */
public interface UserBLService {
	/**
     * 登录
     * 
     * @param userID, password
     * @return ResultMessage
     */
	public ResultMessage login(String userID, String password);
	
	/**
     * 添加用户
     * 
     * @param userID, password, name, position, limit
     * @return ResultMessage
     */
	public ResultMessage addUser(UserVO vo);
	
	/**
     * 根据ID查找相应用户并删除
     * 
     * @param userID
     * @return ResultMessage
     */
	public ResultMessage deleteUser(String userID);
	
	/**
     * 根据用户ID查找相应用户并更改用户信息
     * 
     * @param userID, password, name, position, limit
     * @return ResultMessage
     */
	public ResultMessage modifyUser(UserVO vo);
	
	/**
     * 根据用户ID查找相应用户
     * 
     * @param userID
     * @return userVO
     */
	public UserVO findUserByID(String UserID);
	
	public ArrayList<UserVO> show();
}
