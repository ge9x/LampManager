package blservice.messageblservice;

import util.BillState;
import util.ResultMessage;
import util.UserPosition;

public interface MessageInfo {

	public ResultMessage addMessage(BillState state, String ID, String messageTime, UserPosition position);
	
}
