package bl.accountbl;

import util.ResultMessage;
import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockAccount extends Account {

    @Override
    public ResultMessage addAccount(AccountVO accountVO) {
        if (accountVO.accountName.equals("工商银行账户")){
            System.out.println("Add account succeed");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.EXIST;
    }

    @Override
    public ResultMessage deleteAccount(String name) {
        if (name.equals("工商银行账户")){
            System.out.println("Delete account Succeed");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.NOT_EXIST;
    }

    @Override
    public ArrayList<AccountVO> findAccountByName(String keyword) {
        if (("工商银行账户").contains(keyword)){
            System.out.println("Find account succeed");
            AccountVO vo = new AccountVO("001","工商银行账户", 2000.00);
            ArrayList vos = new ArrayList();
            vos.add(vo);
            return vos;
        }
        return null;
    }

    @Override
    public ResultMessage updateAccount(AccountVO accountVO) {
        if (accountVO.accountName.equals("工商银行账户")){
            System.out.println("Update account succeed");
            return ResultMessage.SUCCESS;
        }
        return ResultMessage.NOT_EXIST;
    }

    @Override
    public ArrayList<AccountVO> show() {
        AccountVO vo = new AccountVO("001","工商银行账户", 2000.00);
        ArrayList vos = new ArrayList();
        vos.add(vo);
        return vos;
    }
}
