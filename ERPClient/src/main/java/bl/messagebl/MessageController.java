package bl.messagebl;

import java.util.ArrayList;

import blservice.messageblservice.MessageBLService;
import blservice.messageblservice.MessageInfo;
import util.BillState;
import util.ResultMessage;
import util.UserPosition;
import vo.MessageVO;

public class MessageController implements MessageBLService, MessageInfo{

	private Message message;
	
	protected MessageController(){
		message = new Message();
	}
	
	public ArrayList<MessageVO> show(UserPosition position){
		return message.show(position);
	}

	@Override
	public ResultMessage addMessage(BillState state, String ID, String messageTime, UserPosition position) {
		// TODO Auto-generated method stub
		return message.addMessage(state, ID, messageTime, position);
	}

	@Override
	public ResultMessage deleteMessage(int messageID) {
		// TODO Auto-generated method stub
		return message.deleteMessage(messageID);
	}
	
	
}
