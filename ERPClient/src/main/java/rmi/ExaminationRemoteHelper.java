package rmi;

import java.rmi.Remote;

import dataservice.examinationdataservice.ExaminationDataService;

public class ExaminationRemoteHelper {

	private Remote remote;
	private static ExaminationRemoteHelper examinationRemoteHelper = new ExaminationRemoteHelper();
	
	public static ExaminationRemoteHelper getInstance(){
		return examinationRemoteHelper;
	}
	
	private ExaminationRemoteHelper(){
		
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public ExaminationDataService getExaminationDataService(){
		return (ExaminationDataService) remote;
	}
}
