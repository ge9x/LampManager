package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataimpl.messagedataimpl.MessageDataServiceImpl;
import dataservice.messagedataservice.MessageDataService;
import po.MessagePO;
import util.ResultMessage;
import util.UserPosition;

public class MessageDataRemoteObject extends UnicastRemoteObject implements MessageDataService{
	private static final long serialVersionUID = 5499526200765834945L;
	private MessageDataService messageDataService;
	
	protected MessageDataRemoteObject() throws RemoteException{
		super();
		messageDataService = MessageDataServiceImpl.getInstance();
	}

	@Override
	public ArrayList<MessagePO> show(UserPosition position) throws RemoteException {
		return messageDataService.show(position);
	}

	@Override
	public ResultMessage add(MessagePO po) throws RemoteException {
		return messageDataService.add(po);
	}

	@Override
	public ResultMessage delete(int messageID) throws RemoteException {
		return messageDataService.delete(messageID);
	}

}
