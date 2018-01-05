package dataservice.messagedataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MessagePO;
import util.ResultMessage;
import util.UserPosition;

public interface MessageDataService extends Remote{

	public ArrayList<MessagePO> show(UserPosition position) throws RemoteException ;
	
	public ResultMessage add(MessagePO po) throws RemoteException ;
	
	public ResultMessage delete(int messageID) throws RemoteException ;
	
}
