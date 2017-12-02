package po;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "cashbillitem")
public class CashBillItemPO implements Serializable{
	/**
	 * 条目ID
	 */
	private int ID;
	/**
     * 条目名
     */
    private String itemName;

    /**
     * 金额
     */
    private double money;

    /**
     * 备注
     */
    private String remark;
    
    public CashBillItemPO(){ }

    public CashBillItemPO(String itemName, double money, String remark) {
        this.itemName = itemName;
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

	@Column(name = "itemname")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
