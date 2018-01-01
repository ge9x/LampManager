package rmi;

import java.rmi.Remote;

import dataservice.messagedataservice.MessageDataService;

public class MessageRemoteHelper {

	private Remote remote;
	private static MessageRemoteHelper messageRemoteHelper = new MessageRemoteHelper();
	
	public static MessageRemoteHelper getInstance(){
		return messageRemoteHelper;
	}
	
	private MessageRemoteHelper(){
		
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public MessageDataService getMessageDataService(){
		return (MessageDataService) remote;
	}
}
