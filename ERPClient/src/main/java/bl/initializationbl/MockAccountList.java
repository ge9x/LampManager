package bl.initializationbl;

import vo.AccountVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/6.
 */
public class MockAccountList extends AccountList {
    @Override
    public ArrayList<AccountVO> getAccounts() {
        AccountVO account = new AccountVO("001","工商银行账户", 2000.00);
        ArrayList<AccountVO> accountVOS = new ArrayList<AccountVO>();
        accountVOS.add(account);
        return accountVOS;
    }
}
