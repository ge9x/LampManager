package rmi;

import dataservice.initializationdataservice.InitializationDataService;

import java.rmi.Remote;

/**
 * Created by Kry·L on 2017/12/25.
 */
public class InitializationRemoteHelper {
    private Remote remote;
    private static InitializationRemoteHelper initializationRemoteHelper = new InitializationRemoteHelper();
    public static InitializationRemoteHelper getInstance(){
        return initializationRemoteHelper;
    }
    private InitializationRemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public InitializationDataService getInitializationDataService(){
        return (InitializationDataService) remote;
    }
}
