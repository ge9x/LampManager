package rmi;

import dataservice.accountdataservice.AccountDataService;

import java.rmi.Remote;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountRemoteHelper {
    private Remote remote;
    private static AccountRemoteHelper accountRemoteHelper = new AccountRemoteHelper();
    public static AccountRemoteHelper getInstance(){
        return accountRemoteHelper;
    }
    private AccountRemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public AccountDataService getAccountDataService(){
        return (AccountDataService)remote;
    }
}
