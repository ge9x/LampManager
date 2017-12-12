package rmi;

import java.rmi.Remote;

import dataservice.promotiondataservice.PromotionDataService;

public class PromotionRemoteHelper {
	
	private Remote remote;
	private static PromotionRemoteHelper promotionRemoteHelper = new PromotionRemoteHelper();
	
	public static PromotionRemoteHelper getInstance(){
		return promotionRemoteHelper;
	}
	
	private PromotionRemoteHelper(){
		
	}
	
	public void setRemote(Remote remote){
		this.remote = remote;
	}
	
	public PromotionDataService getPromotionDataService(){
		return (PromotionDataService) remote;
	}
}
