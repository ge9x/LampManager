package bl.accountbl;

import dataservice.accountdataservice.AccountDataService;
import po.AccountPO;
import rmi.RemoteHelper;
import util.ResultMessage;
import vo.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Account{

    private AccountVO vo;
    private AccountDataService accountDataService;

    public Account() {
        accountDataService = RemoteHelper.getInstance().getAccountDataService();

    }

    public ResultMessage addAccount(AccountVO accountVO) throws RemoteException{
        AccountPO accountPO = poTovo(accountVO);
        ResultMessage re = accountDataService.addAccount(accountPO);
        return re;
    }

    public ResultMessage deleteAccount(String name) {
        return null;
    }

    public ArrayList<AccountVO> findAccountByName(String keyword) {
        return null;
    }

    public ResultMessage updateAccount(AccountVO accountVO) {
        return null;
    }

    public ArrayList<AccountVO> show() {
        return null;
    }

    public AccountPO poTovo(AccountVO accountVO){
        return new AccountPO(accountVO.accountName,accountVO.money);
    }
}
