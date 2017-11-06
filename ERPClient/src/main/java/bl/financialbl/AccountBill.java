package bl.financialbl;

import blservice.customerblservice.CustomerInfo;
import blservice.userblservice.UserInfo;
import vo.AccountBillVO;

import java.util.ArrayList;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountBill {

    private AccountBillVO accountBill;
    private ArrayList<AccountBillItem> accountBillItems;

    private UserInfo userInfo;
    private CustomerInfo info;

    public AccountBill(){

    }

}
