package bl.accountbl;

import dataservice.accountdataservice.AccountDataService;
import util.ResultMessage;
import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class Account{

    private AccountVO vo;
    private AccountDataService accountDataService;

    public Account() {

    }

    public ResultMessage addAccount(AccountVO accountVO) {
        return null;
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
}
