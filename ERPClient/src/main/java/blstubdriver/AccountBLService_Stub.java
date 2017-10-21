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
        if (accountVO.accountName == "账户1") {
            System.out.println("Add account failed");
            return ResultMessage.FAILED;
        } else {
            System.out.println("Add account failed");
            return ResultMessage.SUCCESS;
        }
    }

    public ResultMessage deleteAccount(String name) {
        if (name.equals("账户1")){
            System.out.println("Delete account success");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Delete account failed");
            return ResultMessage.FAILED;
        }
    }

    public ArrayList<AccountVO> findAccountByName(String keyword) {
        if ("账户1".contains(keyword)){
            ArrayList<AccountVO> accountVOS = new ArrayList<AccountVO>();
            accountVOS.add(new AccountVO("账户1",2000));
        }else{
            return new ArrayList<AccountVO>();
        }
    }

    public ResultMessage updateAccount(AccountVO accountVO) {
        if (accountVO.accountName.equals("账户1")){
            System.out.println("Update account success");
            return ResultMessage.SUCCESS;
        }else{
            System.out.println("Update account failed");
            return ResultMessage.NOT_EXIST;
        }
    }

    public ArrayList<AccountVO> show() {

        ArrayList<AccountVO> accountVOArrayList = new ArrayList<AccountVO>();
        AccountVO vo1 = new AccountVO("账户1",2000);
        AccountVO vo2 = new AccountVO("账户2",1000);
        return null;
    }
}
