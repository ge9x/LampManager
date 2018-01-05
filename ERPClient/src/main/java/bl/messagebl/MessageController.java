package bl.messagebl;

import java.rmi.RemoteException;
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
		try {
			return message.show(position);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultMessage addMessage(BillState state, String ID, String messageTime, UserPosition position) {
		// TODO Auto-generated method stub
		try {
			return message.addMessage(state, ID, messageTime, position);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}

	@Override
	public ResultMessage deleteMessage(int messageID) {
		// TODO Auto-generated method stub
		try {
			return message.deleteMessage(messageID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultMessage.ERROR;
		}
	}
	
	
}
