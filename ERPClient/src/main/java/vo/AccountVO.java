package vo;

import bl.accountbl.AccountBLFactory;

/**
 * Created by Kry·L on 2017/10/20.
 */
public class AccountVO {
    /**
     * 银行账户
     */
    public String accountID;

    /**
     * 银行账户名称
     */
    public String accountName;

    /**
     * 银行账户余额
     */
    public double money;

    public AccountVO(String accoutID, String accountName, double money){
        this.accountName = accountName;
        this.money = money;
        this.accountID = accoutID;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof AccountVO){
            AccountVO accountVO = (AccountVO) o;
            return compareData(accountVO);
        }
        return false;

    }

    private boolean compareData(AccountVO accountVO) {
        Boolean ea =  (accountVO.accountID.equals(accountID)
                && accountVO.accountName.equals(accountName)
                && accountVO.money == money);
        return ea;
    }
}
