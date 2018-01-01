package blservice.messageblservice;

import java.util.ArrayList;

import util.BillState;
import util.ResultMessage;
import util.UserPosition;
import vo.MessageVO;

public interface MessageBLService {

	public ArrayList<MessageVO> show(UserPosition position);
	
	public ResultMessage addMessage(BillState state, String ID, String messageTime, UserPosition position);
	
	public ResultMessage deleteMessage(int messageID);
	
}
