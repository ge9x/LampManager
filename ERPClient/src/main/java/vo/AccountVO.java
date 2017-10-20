package vo;

/**
 * Created by Kry·L on 2017/10/20.
 */
public class AccountVO {

    /**
     * 银行账户名称
     */
    private String accountName;
    /**
     * 银行账户余额
     */
    private double money;

    public AccountVO(String accountName, double money){
        this.accountName = accountName;
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }


}
