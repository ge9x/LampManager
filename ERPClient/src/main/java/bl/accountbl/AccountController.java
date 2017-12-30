package bl.accountbl;

import blservice.accountblservice.AccountBLService;
import blservice.accountblservice.AccountInfo;
import util.ResultMessage;
import vo.AccountVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountController implements AccountBLService, AccountInfo{
    //TODO deleteAccount

    private Account account;

    protected AccountController(){
        account = new Account();
    }

    public ResultMessage addAccount(AccountVO accountVO) {
        try {
            return account.addAccount(accountVO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    public ResultMessage deleteAccount(String ID) {
        try {
            return account.deleteAccount(ID);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<AccountVO> findAccountByName(String keyword) {
        try {
            return account.findAccountByName(keyword);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage updateAccount(AccountVO accountVO) {
        try {
            return account.updateAccount(accountVO);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.ERROR;
        }
    }

    public ArrayList<AccountVO> show() {
        try {
            return account.show();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public AccountVO getAccountByID(String id) {
        try {
            return account.getAccountByID(id);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage changeMoney(String ID, double money) {
        try {
            return account.changeMoney(ID, money);
        } catch (RemoteException e) {
            e.printStackTrace();
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<String> getAllAccountName() {
        try {
            return account.getAllAccountName();
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
