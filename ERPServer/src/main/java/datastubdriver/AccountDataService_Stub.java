package datastubdriver;

import dataservice.accountdataservice.AccountDataService;
import po.AccountPO;
import util.ResultMessage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class AccountDataService_Stub implements AccountDataService {
    ArrayList<AccountPO> accountPOS;
    public AccountDataService_Stub(){
        accountPOS = new ArrayList<AccountPO>();
        AccountPO accountPO = new AccountPO(1,"工商银行账户",3000);
        accountPOS.add(accountPO);
    }
    public ResultMessage addAccount(AccountPO po) throws RemoteException {
        if (! po.getName().equals("工商银行账户")){
            System.out.println("Add succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Add failed");
            return ResultMessage.EXIST;
        }
    }

    public ResultMessage deleteAccount(AccountPO po) throws RemoteException {
        if (po.getName().equals("工商银行账户")){
            System.out.println("Delete succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Delete failed");
            return ResultMessage.NOT_EXIST;
        }
    }

    public ResultMessage updateAccount(AccountPO po) throws RemoteException {
        if (po.getName().equals("工商银行账户")){
            System.out.println("Update succeed");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Update failed");
            return ResultMessage.NOT_EXIST;
        }
    }

    public ArrayList<AccountPO> findByName(String keyword) throws RemoteException {
        if ("工商银行账户".contains(keyword)){
            return accountPOS;
        }
        else {
            return new ArrayList<AccountPO>();
        }
    }

    public ArrayList<AccountPO> show() throws RemoteException {
        return accountPOS;
    }
}
