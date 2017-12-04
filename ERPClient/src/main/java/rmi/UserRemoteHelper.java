package rmi;

import java.rmi.Remote;

import dataservice.userdataservice.UserDataService;

public class UserRemoteHelper {

	private Remote remote;
	private static UserRemoteHelper userRemoteHelper = new UserRemoteHelper();
	
	public static UserRemoteHelper getInstance(){
		return userRemoteHelper;
	}
	
	private UserRemoteHelper(){
		
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public UserDataService getUserDataService(){
		return (UserDataService) remote;
	}
}
