package po;

import javax.persistence.*;

import util.UserLimits;
import util.UserPosition;

@Entity
@Table(name = "user")
public class UserPO {
	public String userID;
	
	public String password;
	
	public String name;
	
	public UserPosition position;
	
	public UserLimits limit;
	
	public UserPO(String password, String name, UserPosition position, UserLimits limit) {
		super();
		this.password = password;
		this.name = name;
		this.position = position;
		this.limit = limit;
	}

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
	@Deprecated
	public UserPO(String userID, String password, String name, UserPosition position, UserLimits limit) {
		super();
		this.userID = userID;
		this.password = password;
		this.name = name;
		this.position = position;
		this.limit = limit;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "position")
	@Enumerated(EnumType.STRING)
	public UserPosition getPosition() {
		return position;
	}

	public void setPosition(UserPosition position) {
		this.position = position;
	}

	@Column(name = "limit")
	@Enumerated(EnumType.STRING)
	public UserLimits getLimit() {
		return limit;
	}

	public void setLimit(UserLimits limit) {
		this.limit = limit;
	}
	
	
}
