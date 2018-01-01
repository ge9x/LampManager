package po;

import util.BillState;
import util.UserPosition;

public class MessagePO {

	int messageID;
	BillState state;
	String messageTime;
	String billID;
	UserPosition position;
	
	public MessagePO(BillState state, String billID, String messageTime, UserPosition position) {
		// TODO Auto-generated constructor stub
		this.billID = billID;
		this.messageTime = messageTime;
		this.position = position;
		this.messageID = messageID;
	}

	public int getMessageID() {
		return messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public BillState getState() {
		return state;
	}

	public void setState(BillState state) {
		this.state = state;
	}

	public String getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(String messageTime) {
		this.messageTime = messageTime;
	}

	public String getBillID() {
		return billID;
	}

	public void setBillID(String billID) {
		this.billID = billID;
	}

	public UserPosition getPosition() {
		return position;
	}

	public void setPosition(UserPosition position) {
		this.position = position;
	}
	
	
}
