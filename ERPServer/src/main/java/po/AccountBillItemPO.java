package po;

import javax.persistence.*;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "accountbillitem")
public class AccountBillItemPO {
	/**
	 * 条目唯一标识ID
	 */
	private int ID;

	/**
     * 银行账户名称
     */
    private String accountName;

    /**
     * 金额
     */
    private double money;

    /**
     * 备注
     */
    private String remark;


    public AccountBillItemPO(String accountName, double money, String remark) {
        this.accountName = accountName;
        this.money = money;
        this.remark = remark;
    }

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	@Column(name = "accountname")
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Column(name = "money")
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Column(name = "remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
