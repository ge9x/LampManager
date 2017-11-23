package po;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "initAccount")
public class InitAccountPO {
    private Date date;
    private List<CustomerPO> customerPOS;
    private List<AccountPO> accountPOS;
    private List<GoodsPO> goodsPOS;
    private List<ClassificationPO> classificationPOS;

    public InitAccountPO(Date date, List<CustomerPO> customerPOS, List<AccountPO> accountPOS, List<GoodsPO> goodsPOS,List<ClassificationPO> classificationPOS) {
        this.date = date;
        this.customerPOS = customerPOS;
        this.accountPOS = accountPOS;
        this.goodsPOS = goodsPOS;
        this.classificationPOS = classificationPOS;
    }
    
    @Id
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
    public List<CustomerPO> getCustomerPOS() {
        return customerPOS;
    }

    public void setCustomerPOS(List<CustomerPO> customerPOS) {
        this.customerPOS = customerPOS;
    }

    @OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
    public List<AccountPO> getAccountPOS() {
        return accountPOS;
    }

    public void setAccountPOS(List<AccountPO> accountPOS) {
        this.accountPOS = accountPOS;
    }

    @OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
    public List<GoodsPO> getGoodsPOS() {
        return goodsPOS;
    }

    public void setGoodsPOS(List<GoodsPO> goodsPOS) {
        this.goodsPOS = goodsPOS;
    }

    @OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "claid")
    public List<ClassificationPO> getClassificationPOS() {
        return classificationPOS;
    }

    public void setClassificationPOS(List<ClassificationPO> classificationPOS) {
        this.classificationPOS = classificationPOS;
    }
}
