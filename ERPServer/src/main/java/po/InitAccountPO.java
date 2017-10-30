package po;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kry·L on 2017/10/30.
 */
public class InitAccountPO {
    private Date date;
    private ArrayList<CustomerPO> customerPOS;
    private ArrayList<AccountPO> accountPOS;
    private ArrayList<GoodsPO> goodsPOS;

    public InitAccountPO(Date date, ArrayList<CustomerPO> customerPOS, ArrayList<AccountPO> accountPOS, ArrayList<GoodsPO> goodsPOS) {
        this.date = date;
        this.customerPOS = customerPOS;
        this.accountPOS = accountPOS;
        this.goodsPOS = goodsPOS;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<CustomerPO> getCustomerPOS() {
        return customerPOS;
    }

    public void setCustomerPOS(ArrayList<CustomerPO> customerPOS) {
        this.customerPOS = customerPOS;
    }

    public ArrayList<AccountPO> getAccountPOS() {
        return accountPOS;
    }

    public void setAccountPOS(ArrayList<AccountPO> accountPOS) {
        this.accountPOS = accountPOS;
    }

    public ArrayList<GoodsPO> getGoodsPOS() {
        return goodsPOS;
    }

    public void setGoodsPOS(ArrayList<GoodsPO> goodsPOS) {
        this.goodsPOS = goodsPOS;
    }
}
