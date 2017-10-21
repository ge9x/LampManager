package po;

import util.UserLimits;
import util.UserPosition;

public class UserPO {
	public String userID;
	
	public String password;
	
	public String name;
	
	public UserPosition position;
	
	public UserLimits limit;

	public UserPO(String userID, String password, String name, UserPosition position, UserLimits limit) {
		super();
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.position = position;
		this.limit = limit;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserPosition getPosition() {
		return position;
	}

	public void setPosition(UserPosition position) {
		this.position = position;
	}

	public UserLimits getLimit() {
		return limit;
	}

	public void setLimit(UserLimits limit) {
		this.limit = limit;
	}
	
	
}
