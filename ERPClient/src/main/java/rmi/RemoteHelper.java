package rmi;

import com.sun.org.apache.regexp.internal.RE;
import dataservice.accountdataservice.AccountDataService;
import dataservice.financedataservice.FinanceDataService;

import java.rmi.Remote;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class RemoteHelper {
    private Remote remote;
    private static RemoteHelper remoteHelper = new RemoteHelper();
    public static RemoteHelper getInstance(){
        return remoteHelper;
    }
    private RemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public AccountDataService getAccountDataService(){
        return (AccountDataService)remote;
    }
    public FinanceDataService getFinanceDataService(){
        return (FinanceDataService)remote;
    }

}
