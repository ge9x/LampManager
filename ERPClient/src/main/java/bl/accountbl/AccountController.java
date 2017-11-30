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

    private Account account;

    public AccountController(){
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

    public ArrayList<String> getAllAccountName() {
        return null;
    }

    public ArrayList<AccountVO> getAllAccount() {
        return null;
    }
}
