package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.messagedataservice.MessageDataService;
import po.MessagePO;
import util.ResultMessage;
import util.UserPosition;

public class MessageDataRemoteObject extends UnicastRemoteObject implements MessageDataService{
	
	private MessageDataService messageDataService;
	
	protected MessageDataRemoteObject() throws RemoteException{
		
	}

	@Override
	public ArrayList<MessagePO> show(UserPosition position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage add(MessagePO po) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultMessage delete(int messageID) {
		// TODO Auto-generated method stub
		return null;
	}

}
