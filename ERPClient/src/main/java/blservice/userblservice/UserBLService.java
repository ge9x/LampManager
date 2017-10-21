package blservice.userblservice;

import util.ResultMessage;

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
	public ResultMessage addUser(String userID, String password, String name, UserPosition position, UserLimits limit);
	
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
	public ResultMessage modifyUser(String userID, String password, String name, UserPosition position, UserLimits limit);
}
