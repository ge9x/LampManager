package blstubdriver;

import blservice.accountblservice.AccountBLservice;
import util.ResultMessage;
import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/10/21.
 */
public class AccountBLService_Stub implements AccountBLservice{
    public ResultMessage addAccount(AccountVO accountVO) {
        if (accountVO.accountName.equals("工商银行账户")) {
            System.out.println("Add account failed");
            return ResultMessage.FAILED;
        } else {
            System.out.println("Add account success");
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage deleteAccount(String name) {
        if (name.equals("工商银行账户")){
            System.out.println("Delete account success");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Delete account failed");
            return ResultMessage.NOT_EXIST;
        }
    }

    public ArrayList<AccountVO> findAccountByName(String keyword) {
        if ("工商银行账户".contains(keyword)){
            ArrayList<AccountVO> accountVOS = new ArrayList<AccountVO>();
            accountVOS.add(new AccountVO("工商银行账户1",2000));
            accountVOS.add(new AccountVO("工商银行账户2",3000));
            accountVOS.add(new AccountVO("工商银行账户3",3000));
            return accountVOS;
        }else{
            return new ArrayList<AccountVO>();
        }
    }

    public ResultMessage updateAccount(AccountVO accountVO) {
        if (accountVO.accountName.equals("工商银行账户")){
            System.out.println("Update account success");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Update account failed");
            return ResultMessage.NOT_EXIST;
        }
    }

    public ArrayList<AccountVO> show() {
        ArrayList<AccountVO> accountVOS = new ArrayList<AccountVO>();
        AccountVO vo1 = new AccountVO("工商银行账户1",2000);
        AccountVO vo2 = new AccountVO("工商银行账户2",1000);
        AccountVO vo3 = new AccountVO("工商银行账户3",4000);
        accountVOS.add(vo1);
        accountVOS.add(vo2);
        accountVOS.add(vo3);
        return accountVOS;
    }
}
