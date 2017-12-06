package rmi;

import java.rmi.Remote;

import dataservice.salesdataservice.SalesDataService;

public class SalesRemoteHelper {
	private Remote remote;
    private static SalesRemoteHelper salesRemoteHelper = new SalesRemoteHelper();
    public static SalesRemoteHelper getInstance(){
        return salesRemoteHelper;
    }
    private SalesRemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public SalesDataService getSalesDataService(){
        return (SalesDataService)remote;
    }
}
