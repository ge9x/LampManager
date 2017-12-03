package bl.financialbl;

import bl.accountbl.AccountController;
import blservice.accountblservice.AccountInfo;
import po.AccountBillItemPO;
import po.AccountBillPO;
import vo.AccountBillItemVO;
import vo.AccountVO;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountBillItem {

    AccountInfo accountInfo;

    public AccountBillItem(){
        accountInfo = new AccountController();
    }

    public static AccountBillItemPO voTopo(AccountBillItemVO vo){
        AccountBillItemPO accountBillItemPO = new AccountBillItemPO(Integer.parseInt(vo.account.accountID), vo.transferMoney, vo.remark);
        return accountBillItemPO;
    }

    public AccountBillItemVO poTovo(AccountBillItemPO po){
        AccountBillItemVO accountBillItemVO = new AccountBillItemVO(accountInfo.getAccountByID(po.getID() + ""), po.getMoney(), po.getRemark());
        return accountBillItemVO;
    }


}
