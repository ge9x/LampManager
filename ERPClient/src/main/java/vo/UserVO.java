package vo;

import util.UserLimits;
import util.UserPosition;

/**
 * Created by Aster on 2017/10/21.
 */
public class UserVO {
	public String userID;
	
	public String password;
	
	public String name;
	
	public UserPosition position;
	
	public UserLimits limit;
	
	public UserVO(String userID, String password, String name, UserPosition position, UserLimits limit){
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.position = position;
		this.limit = limit;
	}
}
