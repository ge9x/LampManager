package rmi;

import dataimpl.accountdataimpl.AccountDataServiceImpl;
import dataservice.accountdataservice.AccountDataService;
import datastubdriver.AccountDataService_Stub;
import po.AccountPO;
import util.ResultMessage;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/29.
 */
public class AccountDataRemoteObject extends UnicastRemoteObject implements AccountDataService{


    private AccountDataService accountDataService;
    protected AccountDataRemoteObject() throws RemoteException{
        accountDataService = AccountDataServiceImpl.getInstance();
    }

    @Override
    public ResultMessage addAccount(AccountPO po) throws RemoteException {
        return accountDataService.addAccount(po);
    }

    @Override
    public ResultMessage deleteAccount(AccountPO po) throws RemoteException {
        return accountDataService.deleteAccount(po);
    }

    @Override
    public ResultMessage updateAccount(AccountPO po) throws RemoteException {
        return accountDataService.updateAccount(po);
    }

    @Override
    public ArrayList<AccountPO> findByName(String keyword) throws RemoteException {
        return accountDataService.findByName(keyword);
    }

    @Override
    public ArrayList<AccountPO> show() throws RemoteException {
        return accountDataService.show();
    }
}
