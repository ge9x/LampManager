package bl.userbl;

import blservice.userblservice.UserBLService;
import blservice.userblservice.UserInfo;

/**
 * Created on 2017/12/29
 * 
 * @author 巽
 *
 */
public class UserBLFactory {
	private static UserController userController;

	public synchronized static UserBLService getBLService() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}

	public synchronized static UserInfo getInfo() {
		if (userController == null) {
			userController = new UserController();
		}
		return userController;
	}
}
