package po;

import java.io.Serializable;

import javax.persistence.*;

import util.UserLimits;
import util.UserPosition;

@Entity
@Table(name = "user")
public class UserPO implements Serializable {
	private static final long serialVersionUID = 7579870908035212560L;

	private int userID;
	
	private String password;
	
	private String name;
	
	private UserPosition position;
	
	private UserLimits limit;
	
	public UserPO(){ }
	
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
	public UserPO(int userID, String password, String name, UserPosition position, UserLimits limit) {
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
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
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

	@Column(name = "userlimit")
	@Enumerated(EnumType.STRING)
	public UserLimits getLimit() {
		return limit;
	}

	public void setLimit(UserLimits limit) {
		this.limit = limit;
	}
	
	
}
