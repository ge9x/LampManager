package po;

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
public class InitAccountPO {
	private int ID;
    private String date;
    private List<CustomerPO> customerPOS;
    private List<AccountPO> accountPOS;
    private List<GoodsPO> goodsPOS;
    private List<ClassificationPO> classificationPOS;
    
    public InitAccountPO(){};

    public InitAccountPO(String date, List<CustomerPO> customerPOS, List<AccountPO> accountPOS, List<GoodsPO> goodsPOS,List<ClassificationPO> classificationPOS) {
        this.date = date;
        this.customerPOS = customerPOS;
        this.accountPOS = accountPOS;
        this.goodsPOS = goodsPOS;
        this.classificationPOS = classificationPOS;
    }
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    public int getID(){
    	return ID;
    }
    
    public void setID(int ID){
    	this.ID=ID;
    }
    
    @Column(name = "date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "initAccount")
    public List<CustomerPO> getCustomerPOS() {
        return customerPOS;
    }

    public void setCustomerPOS(List<CustomerPO> customerPOS) {
        this.customerPOS = customerPOS;
    }

    @OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "initAccount")
    public List<AccountPO> getAccountPOS() {
        return accountPOS;
    }

    public void setAccountPOS(List<AccountPO> accountPOS) {
        this.accountPOS = accountPOS;
    }

    @OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "initAccount")
    public List<GoodsPO> getGoodsPOS() {
        return goodsPOS;
    }

    public void setGoodsPOS(List<GoodsPO> goodsPOS) {
        this.goodsPOS = goodsPOS;
    }

    @OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "initAccount")
    public List<ClassificationPO> getClassificationPOS() {
        return classificationPOS;
    }

    public void setClassificationPOS(List<ClassificationPO> classificationPOS) {
        this.classificationPOS = classificationPOS;
    }
}
