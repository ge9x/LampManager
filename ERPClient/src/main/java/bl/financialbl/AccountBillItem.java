package bl.financialbl;

import blservice.accountblservice.AccountInfo;
import po.AccountBillItemPO;
import po.AccountBillPO;
import vo.AccountBillItemVO;

/**
 * Created by Kry·L on 2017/11/5.
 */
public class AccountBillItem {


    /**
     * 银行账户名称
     */
    public String account;

    /**
     * 转账金额
     */
    public double transferMoney;

    /**
     * 备注
     */
    public String remark;

    public AccountBillItem(String account,double transferMoney, String remark){
        this.account = account;
        this.transferMoney = transferMoney;
        this.remark = remark;
    }

    public AccountBillItemPO voTopo(AccountBillItemVO vo){
        AccountBillItemPO accountBillItemPO = new AccountBillItemPO(Integer.parseInt(vo.account.accountID), vo.transferMoney, vo.remark);
        return accountBillItemPO;
    }


}
