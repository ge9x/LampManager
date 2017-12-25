package po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Kry·L on 2017/10/30.
 */

@Entity
@Table(name = "initaccount")
public class InitAccountPO implements Serializable{
	private static final long servialVersionUID=453467868782732L;
	
	private int ID;
	/**
     * 银行账户ID
     */
    private  int accountID;
    /**
     * 银行账户名称
     */
    private String name;

    /**
     * 银行账户余额
     */
    private double money;
    
    public InitAccountPO(){ }
    
    public InitAccountPO(int accountID,String name, double money) {
    	this.accountID=accountID;
        this.name = name;
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
   	
    @Column(name = "accountId")
    public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	@Column(name = "name")
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "money")
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
