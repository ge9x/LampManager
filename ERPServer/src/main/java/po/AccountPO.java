package po;

/**
 * Created by Kry·L on 2017/10/22.
 */
public class AccountPO {
    /**
     * 银行账户ID
     */
    private  int ID;
    /**
     * 银行账户名称
     */
    private String name;

    /**
     * 银行账户余额
     */
    private double money;

    public AccountPO(int ID, String name, double money) {
        this.name = name;
        this.money = money;
        this.ID = ID;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
