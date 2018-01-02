package po;

import java.io.Serializable;

import javax.persistence.*;

import util.BillState;
import util.UserPosition;

@Entity
@Table(name = "message")
public class MessagePO implements Serializable {
	private static final long serialVersionUID = 4988040023443037988L;
	int messageID;
	BillState state;
	String messageTime;
	String billID;
	UserPosition position;

	public MessagePO() {}

	public MessagePO(BillState state, String billID, String messageTime, UserPosition position) {
		this.billID = billID;
		this.messageTime = messageTime;
		this.position = position;
		this.state = state;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	@Column(name = "state")
	@Enumerated(EnumType.STRING)
	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	@Column(name = "time")
	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	@Column(name = "billID")
	public String getBillID() {
		return billID;
	}

	public void setBillID(String billID) {
		this.billID = billID;
	}

	@Column(name = "position")
	@Enumerated(EnumType.STRING)
	public UserPosition getPosition() {
		return position;
	}

	public void setPosition(UserPosition position) {
		this.position = position;
	}

}
