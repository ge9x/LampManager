package po;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import util.OperationObjectType;
import util.OperationType;

/**
 * Created on 2017/12/24
 * 
 * @author 巽
 *
 */
@Entity
@Table(name = "log")
public class LogPO implements Serializable {
	private static final long serialVersionUID = 585138213541372560L;
	/**
	 * 一条记录的ID
	 */
	private int ID;
	/**
	 * 记录时间
	 */
	private LocalDateTime dateAndTime;
	/**
	 * 操作员ID
	 */
	private String userID;
	/**
	 * 操作类型
	 */
	private OperationType operationType;
	/**
	 * 操作对象类型
	 */
	private OperationObjectType operationObjectType;
	/**
	 * 对象详情
	 */
	private String details;

	public LogPO() {
	}

	public LogPO(LocalDateTime dateAndTime, String userID, OperationType operationType,
			OperationObjectType operationObjectType, String details) {
		super();
		this.dateAndTime = dateAndTime;
		this.userID = userID;
		this.operationType = operationType;
		this.operationObjectType = operationObjectType;
		this.details = details;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	@Column(name = "dateAndTime")
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	@Column(name = "userID")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Column(name = "operationType")
	@Enumerated(EnumType.STRING)
	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	@Column(name = "operationObjectType")
	@Enumerated(EnumType.STRING)
	public OperationObjectType getOperationObjectType() {
		return operationObjectType;
	}

	public void setOperationObjectType(OperationObjectType operationObjectType) {
		this.operationObjectType = operationObjectType;
	}

	@Column(name = "details")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "[" + dateAndTime.toString() + "]" + " [" + userID + "]" + " [" + operationType + "]" + " ["
				+ operationObjectType + "]" + " [" + details + "]";
	}

}
