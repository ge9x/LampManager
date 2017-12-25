package rmi;

import dataservice.financedataservice.FinanceDataService;

import java.rmi.Remote;

/**
 * Created by Kry·L on 2017/12/1.
 */
public class FinanceRemoteHelper {
    private Remote remote;
    private static FinanceRemoteHelper financeRemoteHelper = new FinanceRemoteHelper();
    public static FinanceRemoteHelper getInstance(){
        return financeRemoteHelper;
    }
    private FinanceRemoteHelper(){

    }
    public void setRemote(Remote remote){
        this.remote = remote;
    }
    public FinanceDataService getFinanceDataService(){
        return (FinanceDataService) remote;
    }
}
