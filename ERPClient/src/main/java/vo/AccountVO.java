package vo;

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
}
