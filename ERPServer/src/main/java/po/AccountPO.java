package po;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class AccountPO {
    /**
     * 银行账户名称
     */
    private String name;

    /**
     * 银行账户余额
     */
    private double money;

    public AccountPO(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
