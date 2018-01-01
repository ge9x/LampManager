package dataservice.messagedataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import javafx.scene.effect.Shadow;
import po.MessagePO;
import util.ResultMessage;
import util.UserPosition;

public interface MessageDataService extends Remote{

	public ArrayList<MessagePO> show(UserPosition position);
	
	public ResultMessage add(MessagePO po);
	
	public ResultMessage delete(int messageID);
	
}
