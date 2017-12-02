package po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "accountbillitem")
public class AccountBillItemPO implements Serializable{
	/**
	 * 条目唯一标识ID
	 */
	private int ID;

	/**
     * 银行账户名称
     */
    private int accountID;

    /**
     * 金额
     */
    private double money;

    /**
     * 备注
     */
    private String remark;

    public AccountBillItemPO(){ }

    public AccountBillItemPO(int accountID, double money, String remark) {
        this.accountID = accountID;
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
	
	@Column(name = "accountid")
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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
