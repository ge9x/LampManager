package po;

import util.Money;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kry·L on 2017/10/22.
 */
@Entity
@Table(name = "account")
public class AccountPO implements Serializable{
	private static final long serialVersionUID = 610879225963605751L;
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
    
    public AccountPO(){ }
    
    public AccountPO(String name, double money) {
        this.name = name;
        this.money = money;
    }

	/**
	 * 请使用无需设置ID的构造方法，因为：<br>
	 * 1、要新增的PO的ID应由数据库自动生成，而非手动填入<br>
	 * 2、要修改的PO应从数据库中得到，而非代码生成
	 */
    @Deprecated
    public AccountPO(int ID, String name, double money) {
        this.name = name;
        this.money = money;
        this.ID = ID;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "money")
    public double getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "ID:" + String.format("%02d", ID) + ", 账户名称:" + name + ", 账户余额:" + Money.getMoneyString(money);
    }
}
