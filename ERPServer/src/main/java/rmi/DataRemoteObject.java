package rmi;

import dataservice.accountdataservice.AccountDataService;
import datastubdriver.AccountDataService_Stub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Kry·L on 2017/11/29.
 */
public class DataRemoteObject extends UnicastRemoteObject{
    private AccountDataService accountDataService;
    protected DataRemoteObject() throws RemoteException{
        accountDataService = new AccountDataService_Stub();
    }
}
